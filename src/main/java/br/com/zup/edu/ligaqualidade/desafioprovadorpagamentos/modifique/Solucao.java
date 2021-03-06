package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.Recebivel;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.factory.DadosRecebimentoAdiantadoFactory;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.factory.RecebivelFactory;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.factory.DadosTransacaoFactory;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class Solucao {



	/**
	 * @param infoTransacoes    dados das transações. A String está formatada da seguinte maneira:
	 *                          <b>"valor,metodoPagamento,numeroCartao,nomeCartao,validade,cvv,idTransacao"</b>
	 *                          <ol>
	 *                          <li> Valor é um decimal</li>
	 *                          <li> O método de pagamento é 'DEBITO' ou 'CREDITO' </li>
	 *                          <li> Validade é uma data no formato dd/MM/yyyy. </li>
	 *                          </ol>
	 * @param infoAdiantamentos informacao da transacao que pode ser recebida adiantada. A String está formatada da seguinte maneira:
	 *                          <b>"idTransacao,taxa"</b>
	 *                          <ol>
	 *                          <li> Taxa é um decimal </li>
	 *                          </ol>
	 * @return Uma lista de array de string com as informações na seguinte ordem:
	 * [status,valorOriginal,valorASerRecebidoDeFato,dataEsperadoRecebimento].
	 * <ol>
	 *  <li>O status pode ser 'pago' ou 'aguardando_pagamento'</li>
	 *  <li>O valor original e o a ser recebido de fato devem vir no formato decimal. Ex: 50.45</li>
	 *  <li>dataEsperadoRecebimento deve ser formatada como dd/MM/yyyy. Confira a classe {@link DateTimeFormatter}</li>
	 * </ol>
	 * <p>
	 * É esperado que o retorno respeite a ordem de recebimento
	 */
	public static List<String[]> executa(List<String> infoTransacoes, List<String> infoAdiantamentos) {
		DadosTransacaoFactory dadosTransacaoFactory = DadosTransacaoFactory.getInstance();
		final List<DadosTransacao> transacoes = dadosTransacaoFactory.criar(infoTransacoes);

		DadosRecebimentoAdiantadoFactory adiantadoFactory = DadosRecebimentoAdiantadoFactory.getInstance();
		final List<DadosRecebimentoAdiantado> adiantados = adiantadoFactory.fromFileList(infoAdiantamentos);

		RecebivelFactory recebivelFactory = RecebivelFactory.getInstance();
		final List<Recebivel> recebiveis = recebivelFactory.criar(transacoes);

		adiantados.forEach(adiantamento -> {
			recebiveis.stream()
					.filter(it -> it.getTransactionId() == adiantamento.idTransacao)
					.findFirst()
					.ifPresent(recebivel -> recebivel.realizarAdiantamento(adiantamento));
		});

		return recebiveis.stream().map(Recebivel::toArray).collect(Collectors.toList());
	}

}

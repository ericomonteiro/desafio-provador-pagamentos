package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.factory;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.mapper.DadosTransacaoMapper;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;

import java.util.ArrayList;
import java.util.List;

public class DadosTransacaoFactory {
    private DadosTransacaoFactory() {
    }

    private static final DadosTransacaoFactory instance;

    static {
        instance = new DadosTransacaoFactory();
    }

    public static DadosTransacaoFactory getInstance() {
        return instance;
    }

    /**
     *
     * @param infoTransacoes
     *  dados das transações. A String está formatada da seguinte maneira:
     * 	<b>"valor,metodoPagamento,numeroCartao,nomeCartao,validade,cvv,idTransacao"</b>
     * @return DadosTransacao
     */
    public List<DadosTransacao> criar(List<String> infoTransacoes) {
        final List<DadosTransacao> transacoes = new ArrayList<>();
        for (String dados : infoTransacoes) {
            String[] transacoesEmTexto = dados.split(",");
            DadosTransacao transacao = DadosTransacaoMapper.fromStringLine(dados);
            transacoes.add(transacao);
        }

        return transacoes;
    }
}

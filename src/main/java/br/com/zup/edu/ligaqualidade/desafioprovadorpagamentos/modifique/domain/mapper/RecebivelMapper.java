package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.mapper;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.Recebivel;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;

import java.math.BigDecimal;
import java.time.LocalDate;
import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.enumerate.StatusRecebivel.aguardando_pagamento;
import static br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.enumerate.StatusRecebivel.pago;

public class RecebivelMapper {

    public static Recebivel fromDadosTransacao(DadosTransacao transacao) {
        Recebivel recebivel;
        if (transacao.metodoEhDebito()) {
            recebivel = new Recebivel(transacao.id, pago, LocalDate.now(), transacao.valor, 0.03);
        } else if (transacao.metodoEhCredito()) {
            recebivel = new Recebivel(transacao.id, aguardando_pagamento, LocalDate.now().plusDays(30), transacao.valor, 0.05);
        } else {
            throw new RuntimeException("Não foi possível criar um recebível para esse transação (" + transacao.toString() + ")");
        }

        return recebivel;
    }

}

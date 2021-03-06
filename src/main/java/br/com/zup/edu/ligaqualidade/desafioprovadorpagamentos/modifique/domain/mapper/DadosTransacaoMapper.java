package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.mapper;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DadosTransacaoMapper {

    public static DadosTransacao fromStringLine(String line) {
        String[] splitLine =  line.split(",");
        BigDecimal valor = BigDecimal.valueOf(Double.parseDouble(splitLine[0]));
        MetodoPagamento metodoPagamento = MetodoPagamento.valueOf(splitLine[1]);
        String numero = splitLine[2];
        String nome = splitLine[3];
        LocalDate validade = LocalDate.parse(splitLine[4], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        int cvv = Integer.parseInt(splitLine[5]);
        int id = Integer.parseInt(splitLine[6]);

        return new DadosTransacao(valor, metodoPagamento, numero, nome, validade, cvv, id);
    }

}

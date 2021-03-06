package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.mapper;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;

import java.math.BigDecimal;

public class DadosRecebimentoAdiantadoMapper {

    public static DadosRecebimentoAdiantado fromStringLine(String line) {

        final int ID_INDEX = 0;
        final int TAXA_INDEX = 1;

        String[] splitLine = line.split(",");

        int idTransacao = Integer.parseInt(splitLine[ID_INDEX]);
        BigDecimal taxa = new BigDecimal(splitLine[TAXA_INDEX]);
        return new DadosRecebimentoAdiantado(idTransacao, taxa);
    }

}

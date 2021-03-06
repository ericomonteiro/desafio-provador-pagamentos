package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.factory;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.mapper.DadosRecebimentoAdiantadoMapper;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;

import java.util.List;
import java.util.stream.Collectors;

public class DadosRecebimentoAdiantadoFactory {

    private static final DadosRecebimentoAdiantadoFactory instance;

    static {
        instance = new DadosRecebimentoAdiantadoFactory();
    }

    public static DadosRecebimentoAdiantadoFactory getInstance() {
        return instance;
    }

    public List<DadosRecebimentoAdiantado> fromFileList(List<String> lines) {
        return lines.stream()
                .map(DadosRecebimentoAdiantadoMapper::fromStringLine)
                .collect(Collectors.toList());

    }

}

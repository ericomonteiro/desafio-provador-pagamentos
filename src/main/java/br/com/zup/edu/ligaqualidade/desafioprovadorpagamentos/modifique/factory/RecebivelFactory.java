package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.factory;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.Recebivel;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.mapper.RecebivelMapper;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosTransacao;
import java.util.List;
import java.util.stream.Collectors;

public class RecebivelFactory {
    private RecebivelFactory() {}

    private static final RecebivelFactory instance;

    static {
        instance = new RecebivelFactory();
    }

    public static RecebivelFactory getInstance() {
        return instance;
    }

    public List<Recebivel> criar(List<DadosTransacao> transacoes) {
        return transacoes.stream()
                .map(RecebivelMapper::fromDadosTransacao)
                .collect(Collectors.toList());
    }
}

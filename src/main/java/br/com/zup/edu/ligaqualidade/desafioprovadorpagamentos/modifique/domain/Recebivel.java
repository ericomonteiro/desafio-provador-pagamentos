package br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain;

import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.modifique.domain.enumerate.StatusRecebivel;
import br.com.zup.edu.ligaqualidade.desafioprovadorpagamentos.pronto.DadosRecebimentoAdiantado;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Recebivel {
    private final Integer transactionId;
    private StatusRecebivel statusRecebivel;
    private LocalDate dataRecebimento;
    private final BigDecimal valorOriginal;
    private BigDecimal valorASerRecebido;
    private final Double desconto;

    public Recebivel(Integer transactionId, StatusRecebivel statusRecebivel, LocalDate dataRecebimento, BigDecimal valorOriginal, Double desconto) {
        this.transactionId = transactionId;
        this.statusRecebivel = statusRecebivel;
        this.dataRecebimento = dataRecebimento;
        this.valorOriginal = valorOriginal;

        BigDecimal valorDesconto = valorOriginal.multiply(BigDecimal.valueOf(desconto));
        this.valorASerRecebido = valorOriginal.subtract(valorDesconto);
        System.out.println(valorASerRecebido.toString());
        this.desconto = desconto;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public StatusRecebivel getStatusRecebivel() {
        return statusRecebivel;
    }

    public LocalDate getDataRecebimento() {
        return dataRecebimento;
    }

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public BigDecimal getValorASerRecebido() {
        return valorASerRecebido;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void realizarAdiantamento(DadosRecebimentoAdiantado adiantamento) {
        this.statusRecebivel = StatusRecebivel.pago;
        this.dataRecebimento = this.dataRecebimento.minusDays(30);

        BigDecimal taxaAdiantamento = this.valorASerRecebido.multiply(adiantamento.taxa);
        this.valorASerRecebido = this.valorASerRecebido.subtract(taxaAdiantamento);
    }

    /**
     *
     * @return {status, valorOriginal, valorASerRecebido, data}
     */
    public String[] toArray() {
        return new String[]{statusRecebivel.name(),
                valorOriginal.toString(),
                valorASerRecebido.toString(),
                dataRecebimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))};
    }
}

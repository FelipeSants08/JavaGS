package br.com.fiap.to;

import jakarta.validation.constraints.*;

public abstract class OrcamentoTO {
    private Long codigo;

    @NotNull(message = "O consumo mensal não pode ser nulo.")
    @Positive(message = "O consumo mensal deve ser maior que zero.")
    private Double consumoMensal; // Consumo médio em kWh

    @Positive(message = "O custo total deve ser positivo.")
    private Double custoTotal; // Custo final do sistema

    @Positive(message = "A quantidade de placas deve ser maior que zero.")
    private Integer quantidadePlacas; // Quantidade de placas necessárias

    @NotNull(message = "O tipo de orçamento não pode ser nulo.")
    private String tipoOrcamento;  // Tipo: "ON_GRID" ou "OFF_GRID"

    // Métodos abstratos
    public abstract void calcularQuantidadePlacas(Double potenciaPlaca);
    public abstract void calcularCustoTotal();

    // Getters e setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Double getConsumoMensal() {
        return consumoMensal;
    }

    public void setConsumoMensal(Double consumoMensal) {
        this.consumoMensal = consumoMensal;
    }

    public Double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(Double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Integer getQuantidadePlacas() {
        return quantidadePlacas;
    }

    public void setQuantidadePlacas(Integer quantidadePlacas) {
        this.quantidadePlacas = quantidadePlacas;
    }

    public String getTipoOrcamento() {
        return tipoOrcamento;
    }

    public void setTipoOrcamento(String tipoOrcamento) {
        this.tipoOrcamento = tipoOrcamento;
    }
}

package br.com.fiap.to;

public abstract class OrcamentoTO {
    protected Long codigo;
    protected Double consumoMensal; // Consumo médio em kWh
    protected Double custoTotal; // Custo final do sistema
    protected Integer quantidadePlacas; // Quantidade de placas necessárias

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
}

package br.com.fiap.to;

public abstract class OrcamentoTO {
    private Long codigo;
    private Double consumoMensal;
    private Double custoPorPlaca;
    private Double potenciaPlaca;
    private int quantidadePlacas;
    private Double custoTotal;
    private Double custoInversor;

    public OrcamentoTO(Long codigo, Double consumoMensal, Double custoPorPlaca, Double potenciaPlaca, Double custoInversor) {
        this.codigo = codigo;
        this.consumoMensal = consumoMensal;
        this.custoPorPlaca = custoPorPlaca;
        this.potenciaPlaca = potenciaPlaca;
        this.custoInversor = custoInversor;
    }

    // Métodos abstratos para cálculos específicos
    public abstract void calcularQuantidadePlacas();
    public abstract void calcularCustoTotal();

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public double getConsumoMensal() {
        return consumoMensal;
    }

    public void setConsumoMensal(Double consumoMensal) {
        this.consumoMensal = consumoMensal;
    }

    public double getCustoPorPlaca() {
        return custoPorPlaca;
    }

    public void setCustoPorPlaca(Double custoPorPlaca) {
        this.custoPorPlaca = custoPorPlaca;
    }

    public double getPotenciaPlaca() {
        return potenciaPlaca;
    }

    public void setPotenciaPlaca(Double potenciaPlaca) {
        this.potenciaPlaca = potenciaPlaca;
    }

    public int getQuantidadePlacas() {
        return quantidadePlacas;
    }

    public void setQuantidadePlacas(int quantidadePlacas) {
        this.quantidadePlacas = quantidadePlacas;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(Double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public double getCustoInversor() {
        return custoInversor;
    }

    public void setCustoInversor(Double custoInversor) {
        this.custoInversor = custoInversor;
    }
}

package br.com.fiap.to;

public class OrcamentoOffGridTO extends OrcamentoTO {
    private Double custoBateria;

    public OrcamentoOffGridTO(Long codigo,Double consumoMensal, Double custoPorPlaca, Double potenciaPlaca, Double custoInversor, Double custoBateria) {
        super(codigo,consumoMensal, custoPorPlaca, potenciaPlaca, custoInversor);
        this.custoBateria = custoBateria;
    }

    @Override
    public void calcularQuantidadePlacas() {
        int quantidadePlacas = (int) Math.ceil(getConsumoMensal() / getPotenciaPlaca());
        setQuantidadePlacas(quantidadePlacas);
    }

    @Override
    public void calcularCustoTotal() {
        calcularQuantidadePlacas();
        Double custoTotal = (getQuantidadePlacas() * getCustoPorPlaca()) + getCustoInversor() + custoBateria;
        setCustoTotal(custoTotal);
    }

    public Double getCustoBateria() {
        return custoBateria;
    }

    public void setCustoBateria(Double custoBateria) {
        this.custoBateria = custoBateria;
    }
}

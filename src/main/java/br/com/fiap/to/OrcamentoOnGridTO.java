package br.com.fiap.to;

public class OrcamentoOnGridTO extends OrcamentoTO {

    public OrcamentoOnGridTO(Long codigo,Double consumoMensal, Double custoPorPlaca, Double potenciaPlaca, Double custoInversor) {
        super(codigo,consumoMensal, custoPorPlaca, potenciaPlaca, custoInversor);
    }

    @Override
    public void calcularQuantidadePlacas() {
        int quantidadePlacas = (int) Math.ceil(getConsumoMensal() / getPotenciaPlaca());
        setQuantidadePlacas(quantidadePlacas);
    }

    @Override
    public void calcularCustoTotal() {
        calcularQuantidadePlacas();
        double custoTotal = (getQuantidadePlacas() * getCustoPorPlaca()) + getCustoInversor();
        setCustoTotal(custoTotal);
    }
}
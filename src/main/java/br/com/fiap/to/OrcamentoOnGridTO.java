package br.com.fiap.to;

public class OrcamentoOnGridTO extends OrcamentoTO {
    private Double custoInversor;

    @Override
    public void calcularQuantidadePlacas(Double potenciaPlaca) {
        // Usando o consumo mensal e a potência da placa
        int quantidade = (int) Math.ceil(getConsumoMensal() / potenciaPlaca);
        setQuantidadePlacas(quantidade);
    }

    @Override
    public void calcularCustoTotal() {
        // Cálculo do custo total (placas + inversor)
        double custo = (getQuantidadePlacas() * 4000) + custoInversor;  // Custo da placa + custo do inversor
        setCustoTotal(custo);
    }

    public Double getCustoInversor() {
        return custoInversor;
    }

    public void setCustoInversor(Double custoInversor) {
        this.custoInversor = custoInversor;
    }
}

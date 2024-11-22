package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrcamentoOnGridTO extends OrcamentoTO {
    @NotNull(message = "O custo não pode ser nulo")
    @Positive(message = "O custo deve ser positivo")
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

package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrcamentoOffGridTO extends OrcamentoTO {
    @NotNull(message = "O custo das baterias não pode ser nulo.")
    @Positive(message = "O custo das baterias deve ser maior que zero.")
    private Double custoBaterias;

    @NotNull(message = "O custo do controlador de carga não pode ser nulo.")
    @Positive(message = "O custo do controlador de carga deve ser maior que zero.")
    private Double custoControladorCarga;

    @Override
    public void calcularQuantidadePlacas(Double potenciaPlaca) {
        int quantidade = (int) Math.ceil(getConsumoMensal() / potenciaPlaca);
        setQuantidadePlacas(quantidade);
    }

    @Override
    public void calcularCustoTotal() {
        double custo = (getQuantidadePlacas() * 4000) + custoBaterias + custoControladorCarga;
        setCustoTotal(custo);
    }

    public Double getCustoBaterias() {
        return custoBaterias;
    }

    public void setCustoBaterias(Double custoBaterias) {
        this.custoBaterias = custoBaterias;
    }

    public Double getCustoControladorCarga() {
        return custoControladorCarga;
    }

    public void setCustoControladorCarga(Double custoControladorCarga) {
        this.custoControladorCarga = custoControladorCarga;
    }
}

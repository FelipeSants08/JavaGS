package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class PlacaTO {
    private Long codigo;  // Código único da placa

    @Positive(message = "O custo por placa deve ser maior que zero.")
    private Double custoPorPlaca;  // Custo por placa

    @NotNull(message = "A potência da placa não pode ser nula.")
    @Positive(message = "A potência da placa deve ser maior que zero.")
    private Double potenciaPlaca;  // Potência da placa solar

    @NotNull(message = "A região deve ser informada.")
    private String regiao;

    public PlacaTO() {}

    public PlacaTO(Long codigo, Double potenciaPlaca, Double custoPorPlaca, String regiao) {
        this.codigo = codigo;
        this.potenciaPlaca = potenciaPlaca;
        this.custoPorPlaca = custoPorPlaca;
        this.regiao = regiao;
    }

    // Getters e Setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Double getCustoPorPlaca() {
        return custoPorPlaca;
    }

    public void setCustoPorPlaca(Double custoPorPlaca) {
        this.custoPorPlaca = custoPorPlaca;
    }

    public Double getPotenciaPlaca() {
        return potenciaPlaca;
    }

    public void setPotenciaPlaca(Double potenciaPlaca) {
        this.potenciaPlaca = potenciaPlaca;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
}

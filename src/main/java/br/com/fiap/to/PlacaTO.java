package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class PlacaTO {
    private Long codigo;

    @Positive(message = "O custo por placa deve ser maior que zero.")
    private Double custoPorPlaca;

    @NotNull(message = "A potência da placa não pode ser nula.")
    @Positive(message = "A potência da placa deve ser maior que zero.")
    private Double potenciaPlaca;

    @Min(value = 1, message = "A quantidade mínima de placas deve ser 1.")
    private Integer quantidadePlacas;

    @NotNull(message = "A região deve ser informada.")
    private String regiao;

    public PlacaTO() {}

    public PlacaTO(Long codigo, Double potenciaPlaca, Integer quantidadePlacas, String regiao, Double custoPorPlaca) {
        this.codigo = codigo;
        this.potenciaPlaca = potenciaPlaca;
        this.quantidadePlacas = quantidadePlacas;
        this.regiao = regiao;
        this.custoPorPlaca = custoPorPlaca;
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

    public Integer getQuantidadePlacas() {
        return quantidadePlacas;
    }

    public void setQuantidadePlacas(Integer quantidadePlacas) {
        this.quantidadePlacas = quantidadePlacas;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
}

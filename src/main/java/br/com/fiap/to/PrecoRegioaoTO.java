package br.com.fiap.to;

public class PrecoRegioaoTO {
    private Long codigo;
    private String regiao;
    private Double precoPlaca;

    public PrecoRegioaoTO(Long codigo, String regiao, Double precoPlaca) {
        this.codigo = codigo;
        this.regiao = regiao;
        this.precoPlaca = precoPlaca;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Double getPrecoPlaca() {
        return precoPlaca;
    }

    public void setPrecoPlaca(Double precoPlaca) {
        this.precoPlaca = precoPlaca;
    }
}

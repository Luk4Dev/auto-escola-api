package br.com.autoescola.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class EnderecoVO {

    @Column(name = "logradouro", nullable = false, length = 120)
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento", length = 80)
    private String complemento;

    @Column(name = "bairro", nullable = false, length = 80)
    private String bairro;

    @Column(name = "cidade", nullable = false, length = 80)
    private String cidade;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    protected EnderecoVO() {
    }

    public EnderecoVO(String logradouro, Integer numero, String complemento,
                      String bairro, String cidade, String uf, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public String getLogradouro() { return logradouro; }
    public Integer getNumero() { return numero; }
    public String getComplemento() { return complemento; }
    public String getBairro() { return bairro; }
    public String getCidade() { return cidade; }
    public String getUf() { return uf; }
    public String getCep() { return cep; }
}

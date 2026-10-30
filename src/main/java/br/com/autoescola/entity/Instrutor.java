package br.com.autoescola.entity;

import br.com.autoescola.enums.Especialidade;
import br.com.autoescola.vo.EnderecoVO;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "instrutores")
public class Instrutor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, unique = true, length = 11)
    private String cnh;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Especialidade especialidade;

    @Embedded
    private EnderecoVO endereco;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    protected Instrutor() {
    }

    public Instrutor(String nome, String email, String telefone, String cnh,
                     Especialidade especialidade, EnderecoVO endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnh = cnh;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = true;
    }

    public void atualizarDados(String nome, String telefone, EnderecoVO endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void inativar() {
        this.ativo = false;
    }

    @PrePersist
    void aoCriar() {
        this.criadoEm = LocalDateTime.now();
        this.atualizadoEm = this.criadoEm;
    }

    @PreUpdate
    void aoAtualizar() {
        this.atualizadoEm = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCnh() { return cnh; }
    public Especialidade getEspecialidade() { return especialidade; }
    public EnderecoVO getEndereco() { return endereco; }
    public boolean isAtivo() { return ativo; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
}

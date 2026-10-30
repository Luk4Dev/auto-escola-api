package br.com.autoescola.dto;

import br.com.autoescola.entity.Aluno;

public record AlunoListagemDTO(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public static AlunoListagemDTO de(Aluno aluno) {
        return new AlunoListagemDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf());
    }
}

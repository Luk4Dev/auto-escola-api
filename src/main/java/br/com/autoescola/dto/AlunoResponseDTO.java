package br.com.autoescola.dto;

import br.com.autoescola.entity.Aluno;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        EnderecoResponseDTO endereco
) {
    public static AlunoResponseDTO de(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf(),
                EnderecoResponseDTO.de(aluno.getEndereco())
        );
    }
}

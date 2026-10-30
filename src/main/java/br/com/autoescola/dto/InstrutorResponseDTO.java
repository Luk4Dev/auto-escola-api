package br.com.autoescola.dto;

import br.com.autoescola.entity.Instrutor;
import br.com.autoescola.enums.Especialidade;

public record InstrutorResponseDTO(
        Long id,
        String nome,
        String email,
        String cnh,
        Especialidade especialidade,
        EnderecoResponseDTO endereco
) {
    public static InstrutorResponseDTO de(Instrutor instrutor) {
        return new InstrutorResponseDTO(
                instrutor.getId(), instrutor.getNome(), instrutor.getEmail(),
                instrutor.getCnh(), instrutor.getEspecialidade(),
                EnderecoResponseDTO.de(instrutor.getEndereco())
        );
    }
}

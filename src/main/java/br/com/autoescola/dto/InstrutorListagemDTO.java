package br.com.autoescola.dto;

import br.com.autoescola.entity.Instrutor;
import br.com.autoescola.enums.Especialidade;

public record InstrutorListagemDTO(
        Long id,
        String nome,
        String email,
        String cnh,
        Especialidade especialidade
) {
    public static InstrutorListagemDTO de(Instrutor instrutor) {
        return new InstrutorListagemDTO(
                instrutor.getId(), instrutor.getNome(), instrutor.getEmail(),
                instrutor.getCnh(), instrutor.getEspecialidade()
        );
    }
}

package br.com.autoescola.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AlunoUpdateDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
        String nome,

        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(regexp = "[0-9()+\\-\\s]{8,20}", message = "Informe um telefone válido.")
        String telefone,

        @NotNull(message = "O endereço é obrigatório.")
        @Valid
        EnderecoDTO endereco
) {
}

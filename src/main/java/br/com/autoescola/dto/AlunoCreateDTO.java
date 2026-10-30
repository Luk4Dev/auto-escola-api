package br.com.autoescola.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AlunoCreateDTO(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        @Size(max = 160, message = "O e-mail deve ter no máximo 160 caracteres.")
        String email,

        @NotBlank(message = "O telefone é obrigatório.")
        @Pattern(regexp = "[0-9()+\\-\\s]{8,20}", message = "Informe um telefone válido.")
        String telefone,

        @NotBlank(message = "O CPF é obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 números, sem pontos ou traço.")
        String cpf,

        @NotNull(message = "O endereço é obrigatório.")
        @Valid
        EnderecoDTO endereco
) {
}

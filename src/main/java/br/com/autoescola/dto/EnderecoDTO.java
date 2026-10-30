package br.com.autoescola.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        @NotBlank(message = "O logradouro é obrigatório.")
        @Size(max = 120, message = "O logradouro deve ter no máximo 120 caracteres.")
        String logradouro,

        @PositiveOrZero(message = "O número deve ser maior ou igual a zero.")
        Integer numero,

        @Size(max = 80, message = "O complemento deve ter no máximo 80 caracteres.")
        String complemento,

        @NotBlank(message = "O bairro é obrigatório.")
        @Size(max = 80, message = "O bairro deve ter no máximo 80 caracteres.")
        String bairro,

        @NotBlank(message = "A cidade é obrigatória.")
        @Size(max = 80, message = "A cidade deve ter no máximo 80 caracteres.")
        String cidade,

        @NotBlank(message = "A UF é obrigatória.")
        @Pattern(regexp = "[A-Za-z]{2}", message = "A UF deve conter duas letras.")
        String uf,

        @NotBlank(message = "O CEP é obrigatório.")
        @Pattern(regexp = "\\d{5}-?\\d{3}", message = "O CEP deve estar no formato 00000-000 ou 00000000.")
        String cep
) {
}

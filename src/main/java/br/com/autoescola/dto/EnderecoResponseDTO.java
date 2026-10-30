package br.com.autoescola.dto;

import br.com.autoescola.vo.EnderecoVO;

public record EnderecoResponseDTO(
        String logradouro,
        Integer numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        String cep
) {
    public static EnderecoResponseDTO de(EnderecoVO endereco) {
        return new EnderecoResponseDTO(
                endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getCidade(), endereco.getUf(), endereco.getCep()
        );
    }
}

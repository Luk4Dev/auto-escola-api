package br.com.autoescola.util;

import br.com.autoescola.dto.EnderecoDTO;
import br.com.autoescola.vo.EnderecoVO;

import java.util.Locale;

public final class EnderecoMapper {

    private EnderecoMapper() {
    }

    public static EnderecoVO paraVO(EnderecoDTO dto) {
        return new EnderecoVO(
                dto.logradouro().trim(),
                dto.numero(),
                dto.complemento() == null || dto.complemento().isBlank() ? null : dto.complemento().trim(),
                dto.bairro().trim(),
                dto.cidade().trim(),
                dto.uf().trim().toUpperCase(Locale.ROOT),
                dto.cep().replace("-", "").trim()
        );
    }
}

package br.com.autoescola.dto;

import java.time.Instant;
import java.util.Map;

public record ApiErrorDTO(
        Instant timestamp,
        int status,
        String erro,
        String mensagem,
        Map<String, String> detalhes
) {
}

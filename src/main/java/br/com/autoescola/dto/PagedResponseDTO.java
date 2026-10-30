package br.com.autoescola.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PagedResponseDTO<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {
    public static <T> PagedResponseDTO<T> de(Page<T> resultado) {
        return new PagedResponseDTO<>(
                resultado.getContent(), resultado.getNumber(), resultado.getSize(),
                resultado.getTotalElements(), resultado.getTotalPages(),
                resultado.isFirst(), resultado.isLast()
        );
    }
}

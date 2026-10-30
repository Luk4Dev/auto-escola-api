package br.com.autoescola.exception;

import br.com.autoescola.dto.ApiErrorDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> tratarValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> detalhes = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                detalhes.putIfAbsent(erro.getField(), erro.getDefaultMessage())
        );
        return montar(HttpStatus.BAD_REQUEST, "Dados inválidos.", detalhes);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorDTO> tratarViolacao(ConstraintViolationException ex) {
        Map<String, String> detalhes = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(erro ->
                detalhes.put(erro.getPropertyPath().toString(), erro.getMessage())
        );
        return montar(HttpStatus.BAD_REQUEST, "Parâmetro inválido.", detalhes);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiErrorDTO> tratarNaoEncontrado(RecursoNaoEncontradoException ex) {
        return montar(HttpStatus.NOT_FOUND, ex.getMessage(), Map.of());
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<ApiErrorDTO> tratarConflito(ConflitoException ex) {
        return montar(HttpStatus.CONFLICT, ex.getMessage(), Map.of());
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ApiErrorDTO> tratarRegraNegocio(RegraNegocioException ex) {
        return montar(HttpStatus.BAD_REQUEST, ex.getMessage(), Map.of());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorDTO> tratarIntegridade(DataIntegrityViolationException ex) {
        return montar(HttpStatus.CONFLICT,
                "Não foi possível salvar os dados. Verifique se e-mail, CPF ou CNH já estão cadastrados.",
                Map.of());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorDTO> tratarJsonInvalido(HttpMessageNotReadableException ex) {
        return montar(HttpStatus.BAD_REQUEST,
                "O corpo da requisição está inválido. Confira os tipos e os valores enviados.", Map.of());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorDTO> tratarArgumentoInvalido(IllegalArgumentException ex) {
        return montar(HttpStatus.BAD_REQUEST, ex.getMessage(), Map.of());
    }

    private ResponseEntity<ApiErrorDTO> montar(HttpStatus status, String mensagem,
                                                Map<String, String> detalhes) {
        ApiErrorDTO corpo = new ApiErrorDTO(
                Instant.now(), status.value(), status.getReasonPhrase(), mensagem, detalhes
        );
        return ResponseEntity.status(status).body(corpo);
    }
}

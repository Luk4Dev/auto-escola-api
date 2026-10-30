package br.com.autoescola.controller;

import br.com.autoescola.dto.AlunoCreateDTO;
import br.com.autoescola.dto.AlunoListagemDTO;
import br.com.autoescola.dto.AlunoResponseDTO;
import br.com.autoescola.dto.AlunoUpdateDTO;
import br.com.autoescola.dto.PagedResponseDTO;
import br.com.autoescola.service.AlunoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/alunos")
@Validated
public class AlunoController {

    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> cadastrar(@Valid @RequestBody AlunoCreateDTO dto) {
        AlunoResponseDTO resposta = service.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/alunos/" + resposta.id())).body(resposta);
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<AlunoListagemDTO>> listar(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "A página deve ser zero ou maior.") int page) {
        return ResponseEntity.ok(service.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> atualizar(
            @PathVariable Long id, @Valid @RequestBody AlunoUpdateDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

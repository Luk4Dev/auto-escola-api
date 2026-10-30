package br.com.autoescola.controller;

import br.com.autoescola.dto.InstrutorCreateDTO;
import br.com.autoescola.dto.InstrutorListagemDTO;
import br.com.autoescola.dto.InstrutorResponseDTO;
import br.com.autoescola.dto.InstrutorUpdateDTO;
import br.com.autoescola.dto.PagedResponseDTO;
import br.com.autoescola.service.InstrutorService;
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
@RequestMapping("/api/instrutores")
@Validated
public class InstrutorController {

    private final InstrutorService service;

    public InstrutorController(InstrutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<InstrutorResponseDTO> cadastrar(@Valid @RequestBody InstrutorCreateDTO dto) {
        InstrutorResponseDTO resposta = service.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/instrutores/" + resposta.id())).body(resposta);
    }

    @GetMapping
    public ResponseEntity<PagedResponseDTO<InstrutorListagemDTO>> listar(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "A página deve ser zero ou maior.") int page) {
        return ResponseEntity.ok(service.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrutorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrutorResponseDTO> atualizar(
            @PathVariable Long id, @Valid @RequestBody InstrutorUpdateDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

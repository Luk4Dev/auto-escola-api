package br.com.autoescola.service;

import br.com.autoescola.dto.InstrutorCreateDTO;
import br.com.autoescola.dto.InstrutorListagemDTO;
import br.com.autoescola.dto.InstrutorResponseDTO;
import br.com.autoescola.dto.InstrutorUpdateDTO;
import br.com.autoescola.dto.PagedResponseDTO;
import br.com.autoescola.entity.Instrutor;
import br.com.autoescola.exception.ConflitoException;
import br.com.autoescola.exception.RecursoNaoEncontradoException;
import br.com.autoescola.repository.InstrutorRepository;
import br.com.autoescola.util.EnderecoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class InstrutorService {

    private static final int TAMANHO_PAGINA = 10;
    private final InstrutorRepository repository;

    public InstrutorService(InstrutorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public InstrutorResponseDTO cadastrar(InstrutorCreateDTO dto) {
        String email = normalizarEmail(dto.email());
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new ConflitoException("Já existe um instrutor cadastrado com esse e-mail.");
        }
        if (repository.existsByCnh(dto.cnh())) {
            throw new ConflitoException("Já existe um instrutor cadastrado com essa CNH.");
        }

        Instrutor instrutor = new Instrutor(
                dto.nome().trim(), email, dto.telefone().trim(), dto.cnh(),
                dto.especialidade(), EnderecoMapper.paraVO(dto.endereco())
        );
        return InstrutorResponseDTO.de(repository.save(instrutor));
    }

    @Transactional(readOnly = true)
    public PagedResponseDTO<InstrutorListagemDTO> listar(int pagina) {
        validarPagina(pagina);
        Page<Instrutor> resultado = repository.findByAtivoTrue(
                PageRequest.of(pagina, TAMANHO_PAGINA, Sort.by(Sort.Direction.ASC, "nome"))
        );
        return PagedResponseDTO.de(resultado.map(InstrutorListagemDTO::de));
    }

    @Transactional(readOnly = true)
    public InstrutorResponseDTO buscarPorId(Long id) {
        return InstrutorResponseDTO.de(buscarAtivo(id));
    }

    @Transactional
    public InstrutorResponseDTO atualizar(Long id, InstrutorUpdateDTO dto) {
        Instrutor instrutor = buscarAtivo(id);
        instrutor.atualizarDados(
                dto.nome().trim(), dto.telefone().trim(), EnderecoMapper.paraVO(dto.endereco())
        );
        return InstrutorResponseDTO.de(repository.save(instrutor));
    }

    @Transactional
    public void excluir(Long id) {
        Instrutor instrutor = buscarAtivo(id);
        instrutor.inativar();
        repository.save(instrutor);
    }

    private Instrutor buscarAtivo(Long id) {
        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor não encontrado."));
    }

    private String normalizarEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private void validarPagina(int pagina) {
        if (pagina < 0) {
            throw new IllegalArgumentException("O número da página não pode ser negativo.");
        }
    }
}

package br.com.autoescola.service;

import br.com.autoescola.dto.AlunoCreateDTO;
import br.com.autoescola.dto.AlunoListagemDTO;
import br.com.autoescola.dto.AlunoResponseDTO;
import br.com.autoescola.dto.AlunoUpdateDTO;
import br.com.autoescola.dto.PagedResponseDTO;
import br.com.autoescola.entity.Aluno;
import br.com.autoescola.exception.ConflitoException;
import br.com.autoescola.exception.RecursoNaoEncontradoException;
import br.com.autoescola.exception.RegraNegocioException;
import br.com.autoescola.repository.AlunoRepository;
import br.com.autoescola.util.CpfUtils;
import br.com.autoescola.util.EnderecoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class AlunoService {

    private static final int TAMANHO_PAGINA = 10;
    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AlunoResponseDTO cadastrar(AlunoCreateDTO dto) {
        if (!CpfUtils.isValid(dto.cpf())) {
            throw new RegraNegocioException("O CPF informado é inválido.");
        }

        String email = dto.email().trim().toLowerCase(Locale.ROOT);
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new ConflitoException("Já existe um aluno cadastrado com esse e-mail.");
        }
        if (repository.existsByCpf(dto.cpf())) {
            throw new ConflitoException("Já existe um aluno cadastrado com esse CPF.");
        }

        Aluno aluno = new Aluno(
                dto.nome().trim(), email, dto.telefone().trim(), dto.cpf(),
                EnderecoMapper.paraVO(dto.endereco())
        );
        return AlunoResponseDTO.de(repository.save(aluno));
    }

    @Transactional(readOnly = true)
    public PagedResponseDTO<AlunoListagemDTO> listar(int pagina) {
        if (pagina < 0) {
            throw new IllegalArgumentException("O número da página não pode ser negativo.");
        }
        Page<Aluno> resultado = repository.findAll(
                PageRequest.of(pagina, TAMANHO_PAGINA, Sort.by(Sort.Direction.ASC, "nome"))
        );
        return PagedResponseDTO.de(resultado.map(AlunoListagemDTO::de));
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO buscarPorId(Long id) {
        return AlunoResponseDTO.de(buscar(id));
    }

    @Transactional
    public AlunoResponseDTO atualizar(Long id, AlunoUpdateDTO dto) {
        Aluno aluno = buscar(id);
        aluno.atualizarDados(
                dto.nome().trim(), dto.telefone().trim(), EnderecoMapper.paraVO(dto.endereco())
        );
        return AlunoResponseDTO.de(repository.save(aluno));
    }

    @Transactional
    public void excluir(Long id) {
        Aluno aluno = buscar(id);
        repository.delete(aluno);
    }

    private Aluno buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado."));
    }
}

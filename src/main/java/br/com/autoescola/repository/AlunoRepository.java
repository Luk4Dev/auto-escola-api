package br.com.autoescola.repository;

import br.com.autoescola.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByCpf(String cpf);
}

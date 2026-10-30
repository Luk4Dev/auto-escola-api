package br.com.autoescola.repository;

import br.com.autoescola.entity.Instrutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    Page<Instrutor> findByAtivoTrue(Pageable pageable);
    Optional<Instrutor> findByIdAndAtivoTrue(Long id);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByCnh(String cnh);
}

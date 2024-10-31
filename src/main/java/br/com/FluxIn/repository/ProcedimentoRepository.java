package br.com.FluxIn.repository;

import br.com.FluxIn.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
    Set<Procedimento> findByIdIn(Set<Long> ids);
}

package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
    Set<Procedimento> findByIdIn(Set<Long> ids);
}

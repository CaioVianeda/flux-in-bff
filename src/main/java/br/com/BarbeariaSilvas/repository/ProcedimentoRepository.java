package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
    List<Procedimento> findByIdIn(List<Integer> ids);
}

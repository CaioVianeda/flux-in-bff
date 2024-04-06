package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}

package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}

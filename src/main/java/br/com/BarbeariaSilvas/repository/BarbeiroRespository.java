package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRespository extends JpaRepository<Barbeiro, Long> {
}

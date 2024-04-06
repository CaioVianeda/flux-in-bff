package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

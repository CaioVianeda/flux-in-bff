package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface BarbeiroRespository extends JpaRepository<Barbeiro, Long> {
    @Query( "SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
            "FROM Barbeiro b " +
            "WHERE b.id = :idBarbeiro " +
            "AND :horario BETWEEN b.inicioExpediente AND b.terminoExpediente")
    boolean horarioDeExpediente(Long idBarbeiro, LocalTime horario);
}

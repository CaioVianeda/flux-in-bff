package br.com.FluxIn.repository;

import br.com.FluxIn.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query( "SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
            "FROM Funcionario b " +
            "WHERE b.id = :idBarbeiro " +
            "AND :horario BETWEEN b.inicioExpediente AND b.terminoExpediente")
    boolean horarioDeExpediente(Long idBarbeiro, LocalTime horario);
}

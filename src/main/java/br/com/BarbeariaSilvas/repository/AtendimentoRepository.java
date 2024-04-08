package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    @Query("SELECT COUNT(a) FROM Atendimento a " +
            "WHERE a.agenda.id = :agendaId " +
            "AND a.data BETWEEN :inicioPeriodo AND :fimPeriodo")
    int contaAtendimentosPorAgendaEPeriodo(Long agendaId,
                                            LocalDateTime inicioPeriodo,
                                            LocalDateTime fimPeriodo);

}
package br.com.BarbeariaSilvas.repository;

import br.com.BarbeariaSilvas.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    @Query("SELECT COUNT(a) FROM Atendimento a " +
            "WHERE a.funcionario.id = :funcionarioId " +
            "AND a.data BETWEEN :inicioPeriodo AND :fimPeriodo")
    int contaAtendimentosPorAgendaEPeriodo(Long funcionarioId,
                                           LocalDateTime inicioPeriodo,
                                           LocalDateTime fimPeriodo);

    @Query("SELECT a FROM Atendimento a WHERE a.data BETWEEN :startDate AND :endDate AND a.funcionario.id = :idFuncionario")
    List<Atendimento> findByDataHoraBetween(@Param("idFuncionario") Long id,
                                            @Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);
}
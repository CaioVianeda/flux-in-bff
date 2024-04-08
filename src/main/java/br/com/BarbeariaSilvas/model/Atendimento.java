package br.com.BarbeariaSilvas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne
    private Cliente cliente;
    @Setter
    @ManyToOne
    private Agenda agenda;
    @Setter
    @ManyToMany
    private Set<Procedimento> procedimentos;
    @Setter
    private List<BigDecimal> registroValorProcedimentos = new ArrayList<>();
    private Boolean finalizado;
    @Setter
    private LocalDateTime data;

    public Atendimento(Cliente cliente, Agenda agenda, Set<Procedimento> procedimentos, LocalDateTime data) {
        this.cliente = cliente;
        this.agenda = agenda;
        this.procedimentos = procedimentos;
        this.finalizado = data.isBefore(LocalDateTime.now());
        procedimentos.forEach(p -> registroValorProcedimentos.add(p.getPreco()));
        this.data = data;
    }

    public void mudarStatusFinalizado(){this.finalizado= !finalizado;}
}

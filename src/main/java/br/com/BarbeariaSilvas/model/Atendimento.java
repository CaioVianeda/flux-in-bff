package br.com.BarbeariaSilvas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime data;

    public Atendimento(Cliente cliente, Agenda agenda, Set<Procedimento> procedimentos, LocalDateTime data) {
        this.cliente = cliente;
        this.agenda = agenda;
        this.procedimentos = procedimentos;
        this.data = data;
    }

    public void alterarAtendimento(Cliente cliente, Agenda agenda, Set<Procedimento> procedimentos, LocalDateTime data) {

        if (cliente != this.cliente) {
            this.cliente = cliente;
        }
        if (agenda != this.agenda) {
            this.agenda = agenda;
        }
        if (procedimentos != this.procedimentos) {
            this.procedimentos = procedimentos;
        }
        if (data != this.data) {
            this.data = data;
        }
    }

}

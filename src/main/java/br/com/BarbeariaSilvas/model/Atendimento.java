package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.repository.ClienteRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Agenda agenda;
    @ManyToMany
    private Set<Procedimento> procedimentos;
    private LocalDateTime data;

    public Atendimento(Cliente cliente, Agenda agenda, Set<Procedimento> procedimentos, LocalDateTime data) {
        this.cliente = cliente;
        this.agenda = agenda;
        this.procedimentos = procedimentos;
        this.data = data;
    }
}

package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.repository.ClienteRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Cliente cliente;
    @ManyToOne
    private Agenda agenda;
    @Embedded
    @OneToMany
    private List<Procedimento> procedimentos;
    private LocalDateTime data;

    public Atendimento(Cliente cliente, Agenda agenda, List<Procedimento> procedimentos, LocalDateTime data) {
        this.cliente = cliente;
        this.agenda = agenda;
        this.procedimentos = procedimentos;
        this.data = data;
    }
}

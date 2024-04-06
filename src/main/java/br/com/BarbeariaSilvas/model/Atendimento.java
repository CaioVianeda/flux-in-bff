package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.model.Barbeiro;
import br.com.BarbeariaSilvas.model.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime data;
}

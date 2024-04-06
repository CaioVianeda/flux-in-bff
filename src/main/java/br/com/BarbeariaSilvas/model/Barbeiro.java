package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroBarbeiroDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "barbeiros")
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    @OneToOne
    private Agenda agenda;
    private String email;

    public Barbeiro(CadastroBarbeiroDTO dto, Agenda agenda){
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.agenda = agenda;
        this.email = dto.email();
    }
}

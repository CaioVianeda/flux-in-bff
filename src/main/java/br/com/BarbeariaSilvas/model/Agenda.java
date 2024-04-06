package br.com.BarbeariaSilvas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "agenda")
    private Barbeiro barbeiro;
    @OneToMany(mappedBy = "agenda")
    private List<Atendimento> atendimentos;
}

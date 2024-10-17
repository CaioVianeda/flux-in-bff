package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroBarbeariaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "barbearias")
@Getter
@NoArgsConstructor
public class Barbearia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nome;
    private String email;
    @OneToMany(mappedBy = "barbearia", fetch = FetchType.EAGER)
    private List<Funcionario> barbeiros;

    public Barbearia(String nome, String email, ArrayList<Funcionario> barbeiros) {
        this.nome = nome;
        this.email = email;
        this.barbeiros = new ArrayList<>();
    }

    public Barbearia(CadastroBarbeariaDTO barbeariaDTO) {
        this.nome = barbeariaDTO.nome();
        this.email = barbeariaDTO.email();
        this.barbeiros = new ArrayList<>();
    }

    public void atualizaBarbeiro(CadastroBarbeariaDTO novosDados){
        if(novosDados.nome() != this.nome){
            this.nome = novosDados.nome();
        }
        if(novosDados.email() != this.email){
            this.email = novosDados.email();
        }
    }
}

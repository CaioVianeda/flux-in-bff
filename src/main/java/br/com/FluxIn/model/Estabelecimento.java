package br.com.FluxIn.model;

import br.com.FluxIn.dto.CadastroEstabelecimentoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "barbearias")
@Getter
@NoArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nome;
    private String email;
    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    public Estabelecimento(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.funcionarios = new ArrayList<>();
    }

    public Estabelecimento(CadastroEstabelecimentoDTO barbeariaDTO) {
        this.nome = barbeariaDTO.nome();
        this.email = barbeariaDTO.email();
        this.funcionarios = new ArrayList<>();
    }

    public void atualizaBarbeiro(CadastroEstabelecimentoDTO novosDados){
        if(!Objects.equals(novosDados.nome(), this.nome)){
            this.nome = novosDados.nome();
        }
        if(!Objects.equals(novosDados.email(), this.email)){
            this.email = novosDados.email();
        }
    }
}

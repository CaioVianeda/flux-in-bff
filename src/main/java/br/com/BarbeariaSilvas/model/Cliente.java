package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroClienteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;

    public Cliente(CadastroClienteDTO dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
    }
}

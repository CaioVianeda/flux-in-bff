package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroProcedimentoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "procedimentos")
public class Procedimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    @ManyToOne
    private Atendimento atendimento;


    public Procedimento(CadastroProcedimentoDTO dto) {
        this.nome = dto.nome();
        this.preco = dto.preco();
    }

    public void atualizarProcedimento(CadastroProcedimentoDTO dto) {
        this.nome = dto.nome();
        this.preco = dto.preco();
    }
}

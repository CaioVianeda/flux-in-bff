package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroFuncionarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "barbeiros")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    @ManyToOne
    private Estabelecimento estabelecimento;
    @OneToMany(mappedBy = "funcionario")
    private List<Atendimento> atendimentos;
    private String email;
    private LocalTime inicioExpediente;
    private LocalTime terminoExpediente;


    public Funcionario(Estabelecimento estabelecimento, CadastroFuncionarioDTO dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.estabelecimento = estabelecimento;
        this.atendimentos = new ArrayList<>();
        this.email = dto.email();
        this.inicioExpediente = LocalTime.of(8, 0);
        this.terminoExpediente = LocalTime.of(18, 0);
    }

    public void atualizarDados(CadastroFuncionarioDTO dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        if (dto.inicioExpediente() != null) {
            this.inicioExpediente = dto.inicioExpediente();
        }
        if (dto.terminoExpediente() != null) {
            this.terminoExpediente = dto.terminoExpediente();
        }
    }

    public void atualizarHorario(LocalTime inicioExpediente, LocalTime terminoExpediente) {
        this.inicioExpediente = inicioExpediente;
        this.terminoExpediente = terminoExpediente;
    }

}

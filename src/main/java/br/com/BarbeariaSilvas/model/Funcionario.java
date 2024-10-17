package br.com.BarbeariaSilvas.model;

import br.com.BarbeariaSilvas.dto.CadastroFuncionarioDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

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
    @OneToOne
    private Agenda agenda;
    @ManyToOne
    private Barbearia barbearia;
    private String email;
    private LocalTime inicioExpediente;
    private LocalTime terminoExpediente;


    public Funcionario(Barbearia estabelecimento, CadastroFuncionarioDTO dto, Agenda agenda) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.agenda = agenda;
        this.barbearia = estabelecimento;
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

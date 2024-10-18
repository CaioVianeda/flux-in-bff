package br.com.BarbeariaSilvas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "atendimentos")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @ManyToOne
    private Cliente cliente;
    @Setter
    @ManyToOne
    Funcionario funcionario;
    @Setter
    @ManyToMany
    private Set<Procedimento> procedimentos;
    @Setter
    private List<BigDecimal> registroValorProcedimentos = new ArrayList<>();
    @Setter
    private Boolean finalizado;
    @Setter
    private LocalDateTime data;
    private LocalTime duracao;
    @Setter
    private Boolean confirmado;

    public Atendimento(Cliente cliente,Funcionario funcionario, Set<Procedimento> procedimentos, LocalDateTime data) {
        this.cliente = cliente;
        this.procedimentos = procedimentos;
        this.funcionario = funcionario;
        this.finalizado = data.isBefore(LocalDateTime.now());
        procedimentos.forEach(p -> registroValorProcedimentos.add(p.getPreco()));
        this.data = data;
        this.duracao = LocalTime.of(0,0);
        procedimentos.forEach(p -> this.duracao = this.duracao.plusMinutes(p.getTempoDuracao().getMinute()));
        this.confirmado = true;
    }
}

package br.com.FluxIn.dto;

import br.com.FluxIn.model.Funcionario;

import java.time.LocalTime;

public record DadosFuncionarioDTO(
        Long id,
        Long idBarbearia,
        String nome,
        String telefone,
        String email,
        LocalTime inicioExpediente,
        LocalTime terminoExpediente) {

    public DadosFuncionarioDTO(Funcionario funcionario){
        this(funcionario.getId(),funcionario.getEstabelecimento().getId(),funcionario.getNome(),funcionario.getTelefone(),funcionario.getEmail(), funcionario.getInicioExpediente(),funcionario.getTerminoExpediente());
    }
}

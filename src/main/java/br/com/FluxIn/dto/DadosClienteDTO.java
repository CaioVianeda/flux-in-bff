package br.com.FluxIn.dto;

import br.com.FluxIn.model.Cliente;

public record DadosClienteDTO(
        Long id,
        String nome,
        String telefone) {

    public DadosClienteDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }

}

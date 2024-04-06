package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Cliente;

public record DadosClienteDTO(
        Long id,
        String nome,
        String telefone) {

    public DadosClienteDTO(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }

}

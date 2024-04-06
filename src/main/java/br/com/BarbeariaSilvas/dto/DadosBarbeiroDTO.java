package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Barbeiro;

public record DadosBarbeiroDTO(
        Long id,
        String nome,
        String telefone,
        String email) {

    public DadosBarbeiroDTO(Barbeiro barbeiro){
        this(barbeiro.getId(),barbeiro.getNome(),barbeiro.getTelefone(),barbeiro.getEmail());
    }
}

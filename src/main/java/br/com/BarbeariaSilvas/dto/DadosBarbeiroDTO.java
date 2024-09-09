package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Barbeiro;

public record DadosBarbeiroDTO(
        Long id,
        Long idBarbearia,
        String nome,
        String telefone,
        String email) {

    public DadosBarbeiroDTO(Barbeiro barbeiro){
        this(barbeiro.getId(),barbeiro.getBarbearia().getId(),barbeiro.getNome(),barbeiro.getTelefone(),barbeiro.getEmail());
    }
}

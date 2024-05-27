package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Barbearia;

public record DadosBarbeariaDTO(Long id,String nome, String email) {
    public DadosBarbeariaDTO(Barbearia barbearia){this(barbearia.getId(), barbearia.getNome(), barbearia.getEmail());}
}

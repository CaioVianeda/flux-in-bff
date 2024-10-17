package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Estabelecimento;

public record DadosEstabelecimentoDTO(Long id, String nome, String email) {
    public DadosEstabelecimentoDTO(Estabelecimento barbearia){this(barbearia.getId(), barbearia.getNome(), barbearia.getEmail());}
}

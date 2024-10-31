package br.com.FluxIn.dto;

import br.com.FluxIn.model.Estabelecimento;

public record DadosEstabelecimentoDTO(Long id, String nome, String email) {
    public DadosEstabelecimentoDTO(Estabelecimento estabelecimento){this(estabelecimento.getId(), estabelecimento.getNome(), estabelecimento.getEmail());}
}

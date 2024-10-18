package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Procedimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosProcedimentoDTO(Long id,
                                   @NotBlank
                                   String nome,
                                   @NotNull
                                   BigDecimal preco,
                                   Boolean ativo) {
    public DadosProcedimentoDTO(Procedimento p) {
        this(p.getId(),p.getNome(), p.getPreco(), p.getAtivo());
    }
}

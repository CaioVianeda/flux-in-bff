package br.com.FluxIn.dto;

import br.com.FluxIn.model.Procedimento;
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

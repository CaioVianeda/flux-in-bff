package br.com.FluxIn.dto;

import br.com.FluxIn.model.Procedimento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastroProcedimentoDTO(@NotBlank
                                      String nome,
                                      @NotNull
                                      BigDecimal preco) {
    public CadastroProcedimentoDTO(Procedimento p) {
        this(p.getNome(), p.getPreco());
    }
}

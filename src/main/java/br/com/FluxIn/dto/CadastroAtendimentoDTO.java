package br.com.FluxIn.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record CadastroAtendimentoDTO(@NotNull
                                     Long clienteId,
                                     @NotNull
                                     Long agendaId,
                                     @NotNull
                                     Set<Long> procedimentosId,
                                     @Future
                                     LocalDateTime data) {
}

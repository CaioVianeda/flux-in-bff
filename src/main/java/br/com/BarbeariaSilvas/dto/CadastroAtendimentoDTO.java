package br.com.BarbeariaSilvas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record CadastroAtendimentoDTO(@NotNull
                                  Long clienteId,
                                     @NotNull
                                  Long agendaId,
                                     @NotNull
                                  List<Integer> procedimentosId,
                                     @Future
                                  LocalDateTime data) {
}

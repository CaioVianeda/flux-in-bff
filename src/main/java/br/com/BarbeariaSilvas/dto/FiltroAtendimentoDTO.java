package br.com.BarbeariaSilvas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record FiltroAtendimentoDTO(@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                   LocalDateTime dataInicial,

                                   @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                                   LocalDateTime dataFinal) {
}

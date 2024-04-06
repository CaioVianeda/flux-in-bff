package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Agenda;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroBarbeiroDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String telefone,
        @NotBlank
        @Email
        String email) {
}

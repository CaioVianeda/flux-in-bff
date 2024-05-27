package br.com.BarbeariaSilvas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastroBarbeariaDTO(
        @NotBlank
        String nome,
        @Email
        String email) {
}

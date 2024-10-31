package br.com.FluxIn.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroClienteDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}$")
        String telefone) {
}

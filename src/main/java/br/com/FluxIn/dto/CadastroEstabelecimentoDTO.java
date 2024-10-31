package br.com.FluxIn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastroEstabelecimentoDTO(
        @NotBlank
        String nome,
        @Email
        String email) {
}

package br.com.FluxIn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;

public record CadastroFuncionarioDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}$")
        String telefone,
        @NotBlank
        @Email
        String email,
        LocalTime inicioExpediente,
        LocalTime terminoExpediente) {
}

package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Agenda;
import jakarta.persistence.OneToOne;

public record CadastroBarbeiroDTO(
        String nome,
        String telefone,
        String email) {
}

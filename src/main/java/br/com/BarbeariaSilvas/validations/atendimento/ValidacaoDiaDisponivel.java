package br.com.BarbeariaSilvas.validations.atendimento;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;

public class ValidacaoDiaDisponivel implements ValidacaoAtendimento {
    public void validar(CadastroAtendimentoDTO dto) {
        if (dto.data().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new ValidationException("Não há atendimento aos domingos");
        }
    }
}

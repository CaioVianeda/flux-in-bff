package br.com.FluxIn.validations.atendimento;

import br.com.FluxIn.dto.CadastroAtendimentoDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoDiaDisponivel implements ValidacaoAtendimento {
    public void validar(CadastroAtendimentoDTO dto) {
        if (dto.data().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            throw new ValidationException("Não há atendimento aos domingos");
        }
    }
}

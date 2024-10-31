package br.com.FluxIn.validations.atendimento;

import br.com.FluxIn.dto.CadastroAtendimentoDTO;
import br.com.FluxIn.repository.AtendimentoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoSeNaoHaAtendimentoNoHorario implements ValidacaoAtendimento{

    @Autowired
    AtendimentoRepository atendimentoRepository;

    public void validar(CadastroAtendimentoDTO dto){
        if(atendimentoRepository.contaAtendimentosPorAgendaEPeriodo(dto.agendaId(),dto.data(),dto.data().plusMinutes(60)) > 0){
            throw new ValidationException("Horário indisponivel para este funcionário!");
        }
    }

}

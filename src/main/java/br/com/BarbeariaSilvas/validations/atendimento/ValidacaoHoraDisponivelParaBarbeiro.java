package br.com.BarbeariaSilvas.validations.atendimento;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.AtendimentoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoHoraDisponivelParaBarbeiro implements ValidacaoAtendimento{

    @Autowired
    AtendimentoRepository atendimentoRepository;

    public void validar(CadastroAtendimentoDTO dto){
        if(atendimentoRepository.countAtendimentosByAgendaAndPeriodo(dto.agendaId(),dto.data(),dto.data().plusMinutes(60)) > 0){
            throw new ValidationException("Horário indisponivel para este barbeiro!");
        }
    }

}

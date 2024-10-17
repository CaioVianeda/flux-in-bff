package br.com.BarbeariaSilvas.validations.atendimento;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.model.Agenda;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.FuncionarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoHorarioDeAtendimentoBarbeiro implements ValidacaoAtendimento{

    @Autowired
    FuncionarioRepository barbeiroRespository;
    @Autowired
    AgendaRepository agendaRepository;

    public void validar(CadastroAtendimentoDTO dto){
        Agenda agenda = agendaRepository.findById(dto.agendaId()).get();
        if(!barbeiroRespository.horarioDeExpediente(agenda.getBarbeiro().getId(),dto.data().toLocalTime())){
            throw new ValidationException("Horario indisponivel para este barbeiro! Atendimentos realizados das " + agenda.getBarbeiro().getInicioExpediente().getHour() + "h até às " + agenda.getBarbeiro().getTerminoExpediente());
        }
    }

}

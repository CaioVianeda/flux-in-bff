package br.com.FluxIn.validations.atendimento;

import br.com.FluxIn.dto.CadastroAtendimentoDTO;
import br.com.FluxIn.model.Funcionario;
import br.com.FluxIn.repository.FuncionarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoHorarioDeAtendimentoBarbeiro implements ValidacaoAtendimento{

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public void validar(CadastroAtendimentoDTO dto){
        Funcionario funcionario = funcionarioRepository.findById(dto.agendaId()).get();
        if(!funcionarioRepository.horarioDeExpediente(funcionario.getId(),dto.data().toLocalTime())){
            throw new ValidationException("Horario indisponivel para este funcionário! Atendimentos realizados das " + funcionario.getInicioExpediente().getHour() + "h até às " + funcionario.getTerminoExpediente());
        }
    }

}

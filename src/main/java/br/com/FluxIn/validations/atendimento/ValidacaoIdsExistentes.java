package br.com.FluxIn.validations.atendimento;

import br.com.FluxIn.dto.CadastroAtendimentoDTO;
import br.com.FluxIn.repository.ClienteRepository;
import br.com.FluxIn.repository.FuncionarioRepository;
import br.com.FluxIn.repository.ProcedimentoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoIdsExistentes implements ValidacaoAtendimento {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void validar(CadastroAtendimentoDTO dto) {
        if (dto.clienteId() != null && !clienteRepository.existsById(dto.clienteId())) {
            throw new ValidationException("Não existe cliente com ID : " + dto.clienteId() + "!");
        }
        if (!funcionarioRepository.existsById(dto.agendaId())) {
            throw new ValidationException("Não existe funcionário com ID : " + dto.agendaId() + "!");
        }
        dto.procedimentosId().forEach(id -> {
            if (!procedimentoRepository.existsById(id)) {
                throw new ValidationException("Não existe procedimento com ID: " + id + "!");
            }
        });
    }
}

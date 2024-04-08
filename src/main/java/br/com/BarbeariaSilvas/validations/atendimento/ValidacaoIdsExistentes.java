package br.com.BarbeariaSilvas.validations.atendimento;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.ClienteRepository;
import br.com.BarbeariaSilvas.repository.ProcedimentoRepository;
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
    private AgendaRepository agendaRepository;

    public void validar(CadastroAtendimentoDTO dto) {
        if (dto.clienteId() != null && !clienteRepository.existsById(dto.clienteId())) {
            throw new ValidationException("Não existe cliente com ID : " + dto.clienteId() + "!");
        }
        if (!agendaRepository.existsById(dto.agendaId())) {
            throw new ValidationException("Não existe agenda com ID : " + dto.agendaId() + "!");
        }
        dto.procedimentosId().forEach(id -> {
            if (!procedimentoRepository.existsById(id)) {
                throw new ValidationException("Não existe procedimento com ID: " + id + "!");
            }
        });
    }
}

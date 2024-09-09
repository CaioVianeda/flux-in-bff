package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.dto.DadosAtendimentoDTO;
import br.com.BarbeariaSilvas.model.Atendimento;
import br.com.BarbeariaSilvas.model.Procedimento;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.AtendimentoRepository;
import br.com.BarbeariaSilvas.repository.ClienteRepository;
import br.com.BarbeariaSilvas.repository.ProcedimentoRepository;
import br.com.BarbeariaSilvas.validations.atendimento.ValidacaoAtendimento;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private List<ValidacaoAtendimento> validacoes;

    @Transactional
    public DadosAtendimentoDTO agendarAtendimento(CadastroAtendimentoDTO dto) {
        validacoes.forEach(v -> v.validar(dto));
        var cliente = clienteRepository.findById(dto.clienteId());
        var agenda = agendaRepository.findById(dto.agendaId());
        var procedimentos = procedimentoRepository.findByIdIn(dto.procedimentosId());
        var data = dto.data();
        Atendimento atendimento = new Atendimento(cliente.get(), agenda.get(), procedimentos, data);
        atendimentoRepository.save(atendimento);
        return new DadosAtendimentoDTO(atendimento);
    }

    public List<DadosAtendimentoDTO> listarAtendimentos() {
        return atendimentoRepository.findAll().stream().map(DadosAtendimentoDTO::new).collect(Collectors.toList());
    }

    public DadosAtendimentoDTO listarAtendimentoPorId(Long id) {
        if (atendimentoRepository.existsById(id)) {
            return new DadosAtendimentoDTO(atendimentoRepository.getReferenceById(id));
        } else throw new ValidationException("Não possui atendimento com ID: " + id + "!");
    }

    @Transactional
    public DadosAtendimentoDTO atualizarDadosAtendimento(Long id, CadastroAtendimentoDTO dto) {
        var atendimento = atendimentoRepository.findById(id);
        if (atendimento.isPresent()) {
            atendimento.get().setData(null);
            validacoes.forEach(v -> v.validar(dto));
            if (!Objects.equals(dto.clienteId(), atendimento.get().getCliente().getId())) {
                var cliente = clienteRepository.findById(dto.clienteId());
                atendimento.get().setCliente(cliente.get());
            }
            if (!Objects.equals(dto.agendaId(), atendimento.get().getAgenda().getId())) {
                var agenda = agendaRepository.findById(dto.agendaId());
                atendimento.get().setAgenda(agenda.get());
            }
            if (!dto.procedimentosId()
                    .equals(atendimento.get().getProcedimentos().stream()
                            .map(Procedimento::getId).collect(Collectors.toSet()))) {
                var procedimentos = procedimentoRepository.findByIdIn(dto.procedimentosId());
                atendimento.get().setProcedimentos(procedimentos);
            }
            if (dto.data() != atendimento.get().getData()) {
                atendimento.get().setData(dto.data());
            }
            atendimentoRepository.save(atendimento.get());
            return new DadosAtendimentoDTO(atendimento.get());
        } else throw new ValidationException("Não existe atendimento com ID : " + id + "!");
    }

    @Transactional
    public void apagarAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }


    public DadosAtendimentoDTO alterarConfirmacaoAtendimento(Long id) {
        Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
        if(atendimento.isPresent()){
            atendimento.get().setConfirmado(!atendimento.get().getConfirmado());
            atendimentoRepository.save(atendimento.get());
            return new DadosAtendimentoDTO(atendimento.get());
        } else throw new ValidationException("Não existe atendimento com ID : " + id + "!");
    }

    public DadosAtendimentoDTO alterarFinalizacaoAtendimento(Long id) {
        Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
        if(atendimento.isPresent()){
            atendimento.get().setFinalizado(!atendimento.get().getFinalizado());
            atendimentoRepository.save((atendimento.get()));
            return new DadosAtendimentoDTO(atendimento.get());
        }
        else throw new ValidationException("Não existe atendimento com ID : " + id + "!");
    }
}

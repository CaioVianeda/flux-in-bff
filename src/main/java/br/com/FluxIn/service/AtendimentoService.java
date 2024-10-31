package br.com.FluxIn.service;

import br.com.FluxIn.dto.CadastroAtendimentoDTO;
import br.com.FluxIn.dto.DadosAtendimentoDTO;
import br.com.FluxIn.model.Atendimento;
import br.com.FluxIn.model.Procedimento;
import br.com.FluxIn.repository.AtendimentoRepository;
import br.com.FluxIn.repository.ClienteRepository;
import br.com.FluxIn.repository.FuncionarioRepository;
import br.com.FluxIn.repository.ProcedimentoRepository;
import br.com.FluxIn.validations.atendimento.ValidacaoAtendimento;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private List<ValidacaoAtendimento> validacoes;

    @Transactional
    public DadosAtendimentoDTO agendarAtendimento(CadastroAtendimentoDTO dto) {
        validacoes.forEach(v -> v.validar(dto));
        var cliente = clienteRepository.findById(dto.clienteId());
        var funcionario = funcionarioRepository.findById(dto.agendaId());
        var procedimentos = procedimentoRepository.findByIdIn(dto.procedimentosId());
        var data = dto.data();
        Atendimento atendimento = new Atendimento(cliente.get(), funcionario.get(), procedimentos, data);
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
            if (!Objects.equals(dto.agendaId(), atendimento.get().getFuncionario().getId())) {
                var funcionario = funcionarioRepository.findById(dto.agendaId());
                atendimento.get().setFuncionario(funcionario.get());
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

    public List<DadosAtendimentoDTO> listarAtendimentosPorData(Long idFuncionario, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        if(funcionarioRepository.existsById(idFuncionario)){
            var atendimentos = atendimentoRepository.findByDataHoraBetween(idFuncionario,dataInicial, dataFinal);
            return atendimentos.stream().map(DadosAtendimentoDTO::new).collect(Collectors.toList());
        }
       else throw new ValidationException("Não existe funcionário com id: (" + idFuncionario + ")");
    }
}

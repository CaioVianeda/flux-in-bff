package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.dto.CadastroBarbeiroDTO;
import br.com.BarbeariaSilvas.dto.CadastroProcedimentoDTO;
import br.com.BarbeariaSilvas.dto.DadosAtendimentoDTO;
import br.com.BarbeariaSilvas.model.Atendimento;
import br.com.BarbeariaSilvas.model.Barbeiro;
import br.com.BarbeariaSilvas.model.Procedimento;
import br.com.BarbeariaSilvas.repository.*;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    ProcedimentoRepository procedimentoRepository;

    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public DadosAtendimentoDTO agendarAtendimento(CadastroAtendimentoDTO dto){
        var cliente = clienteRepository.findById(dto.clienteId());
        var agenda = agendaRepository.findById(dto.agendaId());
        var procedimentos = procedimentoRepository.findByIdIn(dto.procedimentosId());
        var data = dto.data();
        Atendimento atendimento = new Atendimento(cliente.get(), agenda.get(),procedimentos, data);
        atendimentoRepository.save(atendimento);
        return new DadosAtendimentoDTO(atendimento);
    }

    public List<DadosAtendimentoDTO> listarAtendimentos() {
        return atendimentoRepository.findAll().stream().map(DadosAtendimentoDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void apagarAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }

    public DadosAtendimentoDTO atualizarAtendimento(Long id, CadastroAtendimentoDTO dto) {
        var atendimento = atendimentoRepository.findById(id);
        if(atendimento.isPresent()){
            if (!Objects.equals(dto.clienteId(), atendimento.get().getCliente().getId())) {
                var cliente = clienteRepository.findById(dto.clienteId());
                atendimento.get().setCliente(cliente.get());
            }
            if (!Objects.equals(dto.agendaId(), atendimento.get().getAgenda().getId())) {
                var agenda = agendaRepository.findById(dto.agendaId());
                atendimento.get().setAgenda(agenda.get());
            }
            if(!dto.procedimentosId()
                    .equals(atendimento.get().getProcedimentos().stream()
                            .map(Procedimento::getId).collect(Collectors.toSet()))){
                var procedimentos = procedimentoRepository.findByIdIn(dto.procedimentosId());
                atendimento.get().setProcedimentos(procedimentos);
            }
            if (dto.data() != atendimento.get().getData()) {
                atendimento.get().setData(dto.data());
            }
            atendimentoRepository.save(atendimento.get());
            return new DadosAtendimentoDTO(atendimento.get());
        } else throw new ValidationException("Não existe atendimento com ID : " + id +"!");
    }
}
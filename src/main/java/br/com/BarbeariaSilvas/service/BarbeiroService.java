package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroBarbeiroDTO;
import br.com.BarbeariaSilvas.dto.DadosBarbeiroDTO;
import br.com.BarbeariaSilvas.model.Agenda;
import br.com.BarbeariaSilvas.model.Barbeiro;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.BarbeiroRespository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BarbeiroService {
    @Autowired
    BarbeiroRespository barbeiroRepository;
    @Autowired
    AgendaRepository agendaRepository;

    @Transactional
    public DadosBarbeiroDTO cadastrarBarbeiro(CadastroBarbeiroDTO dto) {
        Agenda agenda = new Agenda();
        agendaRepository.save(agenda);
        Barbeiro barbeiro = new Barbeiro(dto, agenda);
        barbeiroRepository.save(barbeiro);
        return new DadosBarbeiroDTO(barbeiro);

    }

    public List<DadosBarbeiroDTO> listarBarbeiros() {
        var barbeiros = barbeiroRepository.findAll();
        return barbeiros.stream().map(DadosBarbeiroDTO::new).collect(Collectors.toList());
    }

    public DadosBarbeiroDTO listarBarbeiroPorId(Long id) {
        if (barbeiroRepository.existsById(id)) {
            return new DadosBarbeiroDTO(barbeiroRepository.getReferenceById(id));
        } else throw new ValidationException("Não existe barbeiro com ID : " + id + "!");
    }

    @Transactional
    public void apagarBarbeiro(Long id) {
        barbeiroRepository.deleteById(id);
    }

    @Transactional
    public DadosBarbeiroDTO atualizarDadosBarbeiro(Long id, CadastroBarbeiroDTO dto) {
        var barbeiro = barbeiroRepository.findById(id);
        if (barbeiro.isPresent()) {
            barbeiro.get().atualizarDados(dto);
            barbeiroRepository.save(barbeiro.get());
            return new DadosBarbeiroDTO(barbeiro.get());
        } else throw new ValidationException("Não existe barbeiro com ID : " + id + "!");
    }

    public List<LocalTime> horariosDisponiveisParaBarbeiro(Long id) {
        var barbeiro = barbeiroRepository.findById(id);
       if (barbeiro.isPresent()) {
           List<LocalTime> horarios = new ArrayList<>();
           LocalTime horarioAtual = barbeiro.get().getInicioExpediente();
           while(horarioAtual.isBefore(barbeiro.get().getTerminoExpediente())){
               horarios.add(horarioAtual);
               horarioAtual = horarioAtual.plusMinutes(30);
           }
           return horarios;
       }
       else throw new ValidationException("Não existe barbeiro com ID : " + id + "!");
    }
}

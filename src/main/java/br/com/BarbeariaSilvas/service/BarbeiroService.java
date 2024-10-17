package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroFuncionarioDTO;
import br.com.BarbeariaSilvas.dto.DadosAtendimentoDTO;
import br.com.BarbeariaSilvas.dto.DadosFuncionarioDTO;
import br.com.BarbeariaSilvas.model.Agenda;
import br.com.BarbeariaSilvas.model.Atendimento;
import br.com.BarbeariaSilvas.model.Barbearia;
import br.com.BarbeariaSilvas.model.Funcionario;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.BarbeariaRepository;
import br.com.BarbeariaSilvas.repository.FuncionarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarbeiroService {

    @Autowired
    BarbeariaRepository barbeariaRepository;
    @Autowired
    FuncionarioRepository barbeiroRepository;
    @Autowired
    AgendaRepository agendaRepository;

    @Transactional
    public DadosFuncionarioDTO cadastrarBarbeiro(Long barbeariaID, CadastroFuncionarioDTO dto) {
        Agenda agenda = new Agenda();
        agendaRepository.save(agenda);
        Optional<Barbearia> barbearia = barbeariaRepository.findById(barbeariaID);
        Funcionario barbeiro = new Funcionario(barbearia.get(),dto, agenda);
        barbeiroRepository.save(barbeiro);
        return new DadosFuncionarioDTO(barbeiro);

    }

    public List<DadosFuncionarioDTO> listarBarbeiros() {
        var barbeiros = barbeiroRepository.findAll();
        return barbeiros.stream().map(DadosFuncionarioDTO::new).collect(Collectors.toList());
    }

    public DadosFuncionarioDTO listarBarbeiroPorId(Long id) {
        if (barbeiroRepository.existsById(id)) {
            return new DadosFuncionarioDTO(barbeiroRepository.getReferenceById(id));
        } else throw new ValidationException("Não existe barbeiro com ID : " + id + "!");
    }

    @Transactional
    public void apagarBarbeiro(Long id) {
        barbeiroRepository.deleteById(id);
    }

    @Transactional
    public DadosFuncionarioDTO atualizarDadosBarbeiro(Long id, CadastroFuncionarioDTO dto) {
        var barbeiro = barbeiroRepository.findById(id);
        if (barbeiro.isPresent()) {
            barbeiro.get().atualizarDados(dto);
            barbeiroRepository.save(barbeiro.get());
            return new DadosFuncionarioDTO(barbeiro.get());
        } else throw new ValidationException("Não existe barbeiro com ID : " + id + "!");
    }

    public List<LocalTime> horariosDisponiveisParaBarbeiro(Long id, LocalDate dia) {
        var barbeiro = barbeiroRepository.findById(id);
        if (barbeiro.isPresent()) {

            List<LocalTime> horariosOcupados = retornaHorariosOcupados(dia, id);

            List<LocalTime> horariosDisponiveis = new ArrayList<>();
            LocalTime horarioAtual = barbeiro.get().getInicioExpediente();

            while (horarioAtual.isBefore(barbeiro.get().getTerminoExpediente())) {
                if (!horariosOcupados.contains(horarioAtual) && !dia.isBefore(LocalDate.now())) {
                    if (dia.equals(LocalDate.now()) && horarioAtual.isAfter(LocalTime.now())) {
                        horariosDisponiveis.add(horarioAtual);
                    } else if (!dia.equals(LocalDate.now())) {
                        horariosDisponiveis.add(horarioAtual);
                    }
                }
                horarioAtual = horarioAtual.plusMinutes(30);
            }
            return horariosDisponiveis;
        } else throw new ValidationException("Não existe barbeiro com ID : " + id + "!");
    }

    public List<DadosAtendimentoDTO> listarAtendimentosPorBarbeiro(Long id) {
        List<DadosAtendimentoDTO> atendimentos = new ArrayList<>();

        Agenda agenda = agendaRepository.getReferenceById(id);

        agenda.getAtendimentos().forEach(atendimento -> {
            atendimentos.add(new DadosAtendimentoDTO(atendimento));
        });

        return atendimentos;
    }

    public List<LocalTime> retornaHorariosOcupados(LocalDate dia, Long id) {
        List<Atendimento> atendimentosAgendados = agendaRepository.findById(id).get().getAtendimentos();
        List<LocalTime> horariosOcupados = new ArrayList<>();

        for (Atendimento a : atendimentosAgendados) {
            if (a.getData().toLocalDate().equals(dia)) {
                int duracaoTotal = a.getDuracao().getHour() * 60 + a.getDuracao().getMinute();
                LocalTime horarioInicio = a.getData().toLocalTime();
                LocalTime horarioFim = horarioInicio.plusMinutes(duracaoTotal);

                while (horarioInicio.isBefore(horarioFim)) {
                    horariosOcupados.add(horarioInicio);
                    horarioInicio = horarioInicio.plusMinutes(1);
                }
            }
        }
        return horariosOcupados;
    }


}

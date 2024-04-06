package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroBarbeiroDTO;
import br.com.BarbeariaSilvas.dto.DadosBarbeiroDTO;
import br.com.BarbeariaSilvas.model.Agenda;
import br.com.BarbeariaSilvas.model.Barbeiro;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.BarbeiroRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BarbeiroService {
    @Autowired
    BarbeiroRespository barbeiroRespository;
    @Autowired
    AgendaRepository agendaRepository;

    @Transactional
    public DadosBarbeiroDTO cadastrarBarbeiro(CadastroBarbeiroDTO dto){
        Agenda agenda = new Agenda();
        agendaRepository.save(agenda);
        Barbeiro barbeiro = new Barbeiro(dto,agenda);
        barbeiroRespository.save(barbeiro);
        return new DadosBarbeiroDTO(barbeiro);
    }
}

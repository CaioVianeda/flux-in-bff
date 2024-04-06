package br.com.BarbeariaSilvas.controller;

import br.com.BarbeariaSilvas.dto.CadastroBarbeiroDTO;
import br.com.BarbeariaSilvas.model.Agenda;
import br.com.BarbeariaSilvas.model.Barbeiro;
import br.com.BarbeariaSilvas.repository.AgendaRepository;
import br.com.BarbeariaSilvas.repository.BarbeiroRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {
    @Autowired
    BarbeiroRespository barbeiroRespository;
    @Autowired
    AgendaRepository agendaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarBarbeiro(@RequestBody CadastroBarbeiroDTO dto, UriComponentsBuilder uriBuilder){
        Agenda agenda = new Agenda();
        System.out.println(dto);
        agendaRepository.save(agenda);
        Barbeiro barbeiro = new Barbeiro(dto,agenda);
        System.out.println(barbeiro);
        barbeiroRespository.save(barbeiro);
        var uri = uriBuilder.path("barbeiro/{id}").buildAndExpand(barbeiro.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}

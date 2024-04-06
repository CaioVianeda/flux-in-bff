package br.com.BarbeariaSilvas.controller;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.dto.CadastroBarbeiroDTO;
import br.com.BarbeariaSilvas.service.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    AtendimentoService service;

    @PostMapping
    public ResponseEntity agendarAtendimento(@RequestBody @Valid CadastroAtendimentoDTO dto, UriComponentsBuilder uriBuilder){

        var atendimento = service.agendarAtendimento(dto);
        var uri = uriBuilder.path("atentimento/{id}").buildAndExpand(atendimento.id()).toUri();
        return ResponseEntity.created(uri).body(atendimento);
    }
}

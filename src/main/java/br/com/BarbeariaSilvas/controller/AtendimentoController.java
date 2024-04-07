package br.com.BarbeariaSilvas.controller;

import br.com.BarbeariaSilvas.dto.CadastroAtendimentoDTO;
import br.com.BarbeariaSilvas.dto.CadastroProcedimentoDTO;
import br.com.BarbeariaSilvas.dto.DadosAtendimentoDTO;
import br.com.BarbeariaSilvas.service.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    AtendimentoService service;

    @PostMapping
    public ResponseEntity agendarAtendimento(@RequestBody @Valid CadastroAtendimentoDTO dto, UriComponentsBuilder uriBuilder) {
        var atendimento = service.agendarAtendimento(dto);
        var uri = uriBuilder.path("atentimento/{id}").buildAndExpand(atendimento.id()).toUri();
        return ResponseEntity.created(uri).body(atendimento);
    }

    @GetMapping
    public ResponseEntity<List<DadosAtendimentoDTO>> listarAtendimentos() {
        var atendimentos = service.listarAtendimentos();
        return ResponseEntity.ok(atendimentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarAtendimento(@PathVariable("id") Long id, @RequestBody @Valid CadastroAtendimentoDTO dto){
        DadosAtendimentoDTO atendimento = service.atualizarAtendimento(id, dto);
        return ResponseEntity.ok(atendimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarAtendimento(@PathVariable("id") Long id){
        service.apagarAtendimento(id);
        return ResponseEntity.noContent().build();
    }
}

package br.com.BarbeariaSilvas.controller;

import br.com.BarbeariaSilvas.dto.CadastroBarbeiroDTO;
import br.com.BarbeariaSilvas.dto.DadosBarbeiroDTO;
import br.com.BarbeariaSilvas.service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroController {
    @Autowired
    BarbeiroService service;

    @PostMapping
    public ResponseEntity cadastrarBarbeiro(@RequestBody @Valid CadastroBarbeiroDTO dto, UriComponentsBuilder uriBuilder) {
        var barbeiro = service.cadastrarBarbeiro(dto);
        var uri = uriBuilder.path("barbeiro/{id}").buildAndExpand(barbeiro.id()).toUri();
        return ResponseEntity.created(uri).body(barbeiro);
    }

    @GetMapping
    public ResponseEntity<List<DadosBarbeiroDTO>> listarBarbeiros() {
        var barbeiros = service.listarBarbeiros();
        return ResponseEntity.ok(barbeiros);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarBarbeiroPorId(@PathVariable("id") Long id) {
        DadosBarbeiroDTO barbeiros = service.listarBarbeiroPorId(id);
        return ResponseEntity.ok(barbeiros);
    }


    @PutMapping("/{id}")
    public ResponseEntity atualizarDadosBarbeiro(@PathVariable("id") Long id, @RequestBody CadastroBarbeiroDTO dto) {
        DadosBarbeiroDTO barbeiro = service.atualizarDadosBarbeiro(id, dto);
        return ResponseEntity.ok(barbeiro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarBarbeiro(@PathVariable("id") Long id) {
        service.apagarBarbeiro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/horarios")
    public ResponseEntity<List<LocalTime>> listarHorariosDisponiveis(@PathVariable Long id){
        return ResponseEntity.ok(service.horariosDisponiveisParaBarbeiro(id));
    }
}

package br.com.BarbeariaSilvas.controller;

import br.com.BarbeariaSilvas.dto.CadastroProcedimentoDTO;
import br.com.BarbeariaSilvas.service.ProcedimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    @Autowired
    ProcedimentoService service;

    @PostMapping
    public ResponseEntity cadastrarProcedimento(@RequestBody @Valid CadastroProcedimentoDTO dto, UriComponentsBuilder uriBuilder) {
        var procedimento = service.cadastrarProcedimento(dto);
        return ResponseEntity.ok().body(procedimento);
    }

    @GetMapping
    public ResponseEntity<Set<CadastroProcedimentoDTO>> listarProcedimentos() {
        Set<CadastroProcedimentoDTO> procedimentos = service.listarProcedimentos();
        return ResponseEntity.ok(procedimentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDadosProcedimento(@PathVariable("id") Long id, @RequestBody CadastroProcedimentoDTO dto){
        CadastroProcedimentoDTO procedimento = service.atualizarProcedimento(id,dto);
        return ResponseEntity.ok(procedimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarProcedimento(@PathVariable("id") Long id) {
        service.apagarProcedimento(id);
        return ResponseEntity.noContent().build();
    }
}

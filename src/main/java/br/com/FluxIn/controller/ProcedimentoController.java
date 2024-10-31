package br.com.FluxIn.controller;

import br.com.FluxIn.dto.CadastroProcedimentoDTO;
import br.com.FluxIn.dto.DadosProcedimentoDTO;
import br.com.FluxIn.service.ProcedimentoService;
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
    public ResponseEntity<Set<DadosProcedimentoDTO>> listarProcedimentos() {
        Set<DadosProcedimentoDTO> procedimentos = service.listarProcedimentos();
        return ResponseEntity.ok(procedimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarProcedimentoPorId(@PathVariable("id") Long id) {
        DadosProcedimentoDTO procedimento = service.listarProcedimentoPorId(id);
        return ResponseEntity.ok(procedimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDadosProcedimento(@PathVariable("id") Long id, @RequestBody CadastroProcedimentoDTO dto){
        DadosProcedimentoDTO procedimento = service.atualizarProcedimento(id,dto);
        return ResponseEntity.ok(procedimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarProcedimento(@PathVariable("id") Long id) {
        service.apagarProcedimento(id);
        return ResponseEntity.noContent().build();
    }
}

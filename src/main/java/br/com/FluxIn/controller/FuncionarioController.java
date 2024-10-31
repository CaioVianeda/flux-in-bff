package br.com.FluxIn.controller;

import br.com.FluxIn.dto.CadastroFuncionarioDTO;
import br.com.FluxIn.dto.DadosAtendimentoDTO;
import br.com.FluxIn.dto.DadosFuncionarioDTO;
import br.com.FluxIn.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/barbeiros")
public class FuncionarioController {
    @Autowired
    FuncionarioService service;

    @PostMapping("/{id}")
    public ResponseEntity cadastrarFuncionario(@PathVariable("id") Long barbeariaID, @RequestBody @Valid CadastroFuncionarioDTO dto, UriComponentsBuilder uriBuilder) {
        var barbeiro = service.cadastrarFuncionario(barbeariaID,dto);
        var uri = uriBuilder.path("barbeiro/{id}").buildAndExpand(barbeiro.id()).toUri();
        return ResponseEntity.created(uri).body(barbeiro);
    }

    @GetMapping
    public ResponseEntity<List<DadosFuncionarioDTO>> listarFuncionarios() {
        var barbeiros = service.listarFuncionarios();
        return ResponseEntity.ok(barbeiros);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarFuncionarioPorId(@PathVariable("id") Long id) {
        DadosFuncionarioDTO barbeiros = service.listarFuncionarioPorId(id);
        return ResponseEntity.ok(barbeiros);
    }

    @GetMapping("/{id}/atendimentos")
    public ResponseEntity listarAtendimentosPorFuncionario(@PathVariable("id") Long id) {
        List<DadosAtendimentoDTO> atendimentos= service.listarAtendimentosPorFuncionario(id);
        return ResponseEntity.ok(atendimentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDadosFuncionario(@PathVariable("id") Long id, @RequestBody CadastroFuncionarioDTO dto) {
        DadosFuncionarioDTO barbeiro = service.atualizarDadosFuncionario(id, dto);
        return ResponseEntity.ok(barbeiro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarFuncionario(@PathVariable("id") Long id) {
        service.apagarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/horarios")
    public ResponseEntity<List<String>> listarHorariosDisponiveisPorId(@PathVariable Long id,@RequestParam("dia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dia){
        return ResponseEntity.ok(service.horariosDisponiveisParaFuncionario(id,dia).stream().map(h-> h.format(DateTimeFormatter.ofPattern("HH:mm"))).toList());
    }
}

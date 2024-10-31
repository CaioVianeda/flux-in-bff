package br.com.FluxIn.controller;

import br.com.FluxIn.dto.CadastroAtendimentoDTO;
import br.com.FluxIn.dto.DadosAtendimentoDTO;
import br.com.FluxIn.dto.FiltroAtendimentoDTO;
import br.com.FluxIn.service.AtendimentoService;
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
    public ResponseEntity<DadosAtendimentoDTO> agendarAtendimento(@RequestBody @Valid CadastroAtendimentoDTO dto, UriComponentsBuilder uriBuilder) {
        var atendimento = service.agendarAtendimento(dto);
        var uri = uriBuilder.path("atendimento/{id}").buildAndExpand(atendimento.id()).toUri();
        return ResponseEntity.created(uri).body(atendimento);
    }

    @GetMapping
    public ResponseEntity<List<DadosAtendimentoDTO>> listarAtendimentos() {
        var atendimentos = service.listarAtendimentos();
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosAtendimentoDTO> listarAtendimentoPorId(@PathVariable("id") Long id) {
        DadosAtendimentoDTO atendimento = service.listarAtendimentoPorId(id);
        return ResponseEntity.ok(atendimento);
    }

    @PostMapping("/{id}/filtrar")
    public ResponseEntity<List<DadosAtendimentoDTO>> filtrarAtendimentosPorData(@PathVariable("id") Long idFuncionario,@RequestBody @Valid FiltroAtendimentoDTO datas) {
        List<DadosAtendimentoDTO> atendimentos = service.listarAtendimentosPorData(idFuncionario,datas.dataInicial(), datas.dataFinal());
        return ResponseEntity.ok(atendimentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDadosAtendimento(@PathVariable("id") Long id, @RequestBody @Valid CadastroAtendimentoDTO dto) {
        DadosAtendimentoDTO atendimento = service.atualizarDadosAtendimento(id, dto);
        return ResponseEntity.ok(atendimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarAtendimento(@PathVariable("id") Long id) {
        service.apagarAtendimento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/confirmar")
    public ResponseEntity confirmarAtendimento(@PathVariable("id") Long id){
        var atendimento = service.alterarConfirmacaoAtendimento(id);
        return ResponseEntity.ok(atendimento);
    }

    @PutMapping("{id}/finalizar")
    public ResponseEntity finalizarAtendimento(@PathVariable("id") Long id){
        var atendimento = service.alterarFinalizacaoAtendimento(id);
        return ResponseEntity.ok(atendimento);
    }
}

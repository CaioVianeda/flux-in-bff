package br.com.FluxIn.controller;

import br.com.FluxIn.dto.CadastroEstabelecimentoDTO;
import br.com.FluxIn.dto.DadosEstabelecimentoDTO;
import br.com.FluxIn.dto.DadosFuncionarioDTO;
import br.com.FluxIn.service.EstabelecimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/barbearias")
public class EstabelecimentoController {

    @Autowired
    EstabelecimentoService estabelecimentoService;

    @PostMapping
    public ResponseEntity<DadosEstabelecimentoDTO> cadastrarEstabelecimento(@RequestBody @Valid CadastroEstabelecimentoDTO estabelecimentoDTO, UriComponentsBuilder uriBuilder) {
        var estabelecimento = estabelecimentoService.cadastrarEstabelecimento(estabelecimentoDTO);
        var uri = uriBuilder.path("barbearia/{id}").buildAndExpand(estabelecimento.id()).toUri();
        return ResponseEntity.created(uri).body(estabelecimento);
    }

    @GetMapping
    public ResponseEntity<List<DadosEstabelecimentoDTO>> listarEstabelecimentos() {
        var estabelecimentos = estabelecimentoService.listarEstabelecimento();
        return ResponseEntity.ok(estabelecimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosEstabelecimentoDTO> listarEstabelecimentoPorId(@PathVariable("id") Long id) {
        var estabelecimento = estabelecimentoService.listarEstabelecimentoPorId(id);
        return ResponseEntity.ok(estabelecimento);
    }

    @GetMapping("/{id}/barbeiros")
    public ResponseEntity<List<DadosFuncionarioDTO>> listarFuncionariosPorEstabelecimento(@PathVariable("id") Long id){
        var estabelecimento = estabelecimentoService.listarFuncionarioPorEstabelecimento(id);
        return ResponseEntity.ok(estabelecimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarEstabelecimento(@PathVariable("id") Long id, @RequestBody CadastroEstabelecimentoDTO cadastroEstabelecimentoDTO){
        var estabelecimento = estabelecimentoService.atualizarEstabelecimento(id,cadastroEstabelecimentoDTO);
        return ResponseEntity.ok(estabelecimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarEstabelecimento(@PathVariable("id") Long id) {
        estabelecimentoService.deletarEstabelecimento(id);
        return ResponseEntity.noContent().build();
    }

}

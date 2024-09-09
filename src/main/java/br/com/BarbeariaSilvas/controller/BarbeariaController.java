package br.com.BarbeariaSilvas.controller;

import br.com.BarbeariaSilvas.dto.CadastroBarbeariaDTO;
import br.com.BarbeariaSilvas.dto.DadosBarbeariaDTO;
import br.com.BarbeariaSilvas.service.BarbeariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/barbearias")
public class BarbeariaController {

    @Autowired
    BarbeariaService barbeariaService;

    @PostMapping
    public ResponseEntity cadastrarBarbearia(@RequestBody @Valid CadastroBarbeariaDTO barbeariaDTO, UriComponentsBuilder uriBuilder) {
        var barbearia = barbeariaService.cadastrarBarbearia(barbeariaDTO);
        var uri = uriBuilder.path("barbearia/{id}").buildAndExpand(barbearia.id()).toUri();
        return ResponseEntity.created(uri).body(barbearia);
    }

    @GetMapping
    public ResponseEntity listarBarbearias() {
        var barbearias = barbeariaService.listarBarbearias();
        return ResponseEntity.ok(barbearias);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarBarbeariaPorId(@PathVariable("id") Long id) {
        var barbearias = barbeariaService.listarBarbeariaPorId(id);
        return ResponseEntity.ok(barbearias);
    }

    @GetMapping("/{id}/barbeiros")
    public ResponseEntity listarBarbeirosPorBarbearia(@PathVariable("id") Long id){
        var barbearias = barbeariaService.listarBarbeirosPorBarbearia(id);
        return ResponseEntity.ok(barbearias);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarBarbearia(@PathVariable("id") Long id, @RequestBody CadastroBarbeariaDTO cadastroBarbeariaDTO){

        var barbearia = barbeariaService.atualizarBarbearia(id,cadastroBarbeariaDTO);

        return ResponseEntity.ok(barbearia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarBarbearia(@PathVariable("id") Long id) {
        barbeariaService.deletarBarbearia(id);
        return ResponseEntity.noContent().build();
    }

}

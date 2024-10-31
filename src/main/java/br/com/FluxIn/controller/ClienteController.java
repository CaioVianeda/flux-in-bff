package br.com.FluxIn.controller;

import br.com.FluxIn.dto.CadastroClienteDTO;
import br.com.FluxIn.dto.DadosClienteDTO;
import br.com.FluxIn.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping
    public ResponseEntity criarCliente(@RequestBody @Valid CadastroClienteDTO dto, UriComponentsBuilder uriBuilder) {
        var cliente = service.criarCliente(dto);
        var uri = uriBuilder.path("cliente/{id}").buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<DadosClienteDTO>> listarClientes(){
        var clientes = service.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarClientePorId(@PathVariable("id") Long id){
        DadosClienteDTO cliente = service.listarClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizarDadosCliente(@PathVariable("id") Long id, @RequestBody CadastroClienteDTO dto){
        DadosClienteDTO cliente = service.atualizarDadosCliente(id, dto) ;
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarCliente(@PathVariable("id") Long id){
        service.apagarCliente(id);
        return ResponseEntity.noContent().build();
    }

}

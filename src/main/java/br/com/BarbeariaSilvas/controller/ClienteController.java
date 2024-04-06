package br.com.BarbeariaSilvas.controller;

import br.com.BarbeariaSilvas.dto.CadastroClienteDTO;
import br.com.BarbeariaSilvas.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService service;

    @PostMapping
    public ResponseEntity criarCliente(@RequestBody @Valid CadastroClienteDTO dto, UriComponentsBuilder uriBuilder){
        var cliente = service.criarCliente(dto);
        var uri = uriBuilder.path("cliente/{id}").buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

}

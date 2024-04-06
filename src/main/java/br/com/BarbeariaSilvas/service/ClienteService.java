package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroClienteDTO;
import br.com.BarbeariaSilvas.dto.DadosClienteDTO;
import br.com.BarbeariaSilvas.model.Cliente;
import br.com.BarbeariaSilvas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping
    public DadosClienteDTO criarCliente(CadastroClienteDTO dto){
        Cliente cliente = new Cliente(dto);
        clienteRepository.save(cliente);
        return new DadosClienteDTO(cliente);
    }

}

package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroClienteDTO;
import br.com.BarbeariaSilvas.dto.DadosClienteDTO;
import br.com.BarbeariaSilvas.model.Cliente;
import br.com.BarbeariaSilvas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<DadosClienteDTO> listarClientes() {
        var clientes = clienteRepository.findAll();
        return clientes.stream().map(DadosClienteDTO::new).collect(Collectors.toList());
    }
}

package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroClienteDTO;
import br.com.BarbeariaSilvas.dto.DadosClienteDTO;
import br.com.BarbeariaSilvas.model.Cliente;
import br.com.BarbeariaSilvas.repository.ClienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Transactional
    public void apagarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Transactional
    public DadosClienteDTO atualizarDadosCliente(Long id, CadastroClienteDTO dto) {
        var cliente  = clienteRepository.findById(id);
        if(cliente.isPresent()){
            cliente.get().atualizarCliente(dto);
            return new DadosClienteDTO(cliente.get());
        }
        else throw new ValidationException("Não existe cliente com ID : " + id + "!");
    }
}

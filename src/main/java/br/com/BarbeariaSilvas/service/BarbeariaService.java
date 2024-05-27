package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroBarbeariaDTO;
import br.com.BarbeariaSilvas.dto.DadosBarbeariaDTO;
import br.com.BarbeariaSilvas.dto.DadosBarbeiroDTO;
import br.com.BarbeariaSilvas.model.Barbearia;
import br.com.BarbeariaSilvas.repository.BarbeariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BarbeariaService {

    @Autowired
    BarbeariaRepository barbeariaRepository;

    @Transactional
    public DadosBarbeariaDTO cadastrarBarbearia(CadastroBarbeariaDTO barbeariaDTO) {
        Barbearia barbearia = new Barbearia(barbeariaDTO);
        barbeariaRepository.save(barbearia);
        return new DadosBarbeariaDTO(barbearia);
    }

    public List<DadosBarbeariaDTO> listarBarbearias() {
        return barbeariaRepository.findAll().stream().map(DadosBarbeariaDTO::new).toList();
    }

    public  List<DadosBarbeiroDTO> listarBarbeirosPorBarbearia(Long id) {
        return barbeariaRepository.findById(id).get().getBarbeiros().stream().map(DadosBarbeiroDTO::new).toList();
    }
}

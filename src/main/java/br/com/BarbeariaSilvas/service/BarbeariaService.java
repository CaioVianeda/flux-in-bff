package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroBarbeariaDTO;
import br.com.BarbeariaSilvas.dto.DadosBarbeariaDTO;
import br.com.BarbeariaSilvas.dto.DadosFuncionarioDTO;
import br.com.BarbeariaSilvas.model.Barbearia;
import br.com.BarbeariaSilvas.repository.BarbeariaRepository;
import jakarta.validation.ValidationException;
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

    public DadosBarbeariaDTO listarBarbeariaPorId(Long id) {
        var barbearia = barbeariaRepository.findById(id);

        if(barbearia.isPresent()){
            return new DadosBarbeariaDTO(barbearia.get());
        }
        else {
            throw new ValidationException("Não existe barbearia com ID: " + id + " !");
        }
    }

    public  List<DadosFuncionarioDTO> listarBarbeirosPorBarbearia(Long id) {
        return barbeariaRepository.findById(id).get().getBarbeiros().stream().map(DadosFuncionarioDTO::new).toList();
    }

    @Transactional
    public DadosBarbeariaDTO atualizarBarbearia(Long id,CadastroBarbeariaDTO cadastroBarbeariaDTO) {
        var barbearia =  barbeariaRepository.findById(id);
        if (barbearia.isPresent()){
            barbearia.get().atualizaBarbeiro(cadastroBarbeariaDTO);
            return new DadosBarbeariaDTO(barbearia.get());
        }
        else {
            throw new ValidationException("Não existe barbearia com ID: "+ id + " !");
        }
    }

    @Transactional
    public void deletarBarbearia(Long id){
        if(barbeariaRepository.existsById(id)){
            barbeariaRepository.deleteById(id);
        } else {
            throw new ValidationException("Não existe barbearia com ID: " + id + " !");
        }
    }
}

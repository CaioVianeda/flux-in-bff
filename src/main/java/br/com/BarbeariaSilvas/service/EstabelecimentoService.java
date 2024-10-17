package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroEstabelecimentoDTO;
import br.com.BarbeariaSilvas.dto.DadosEstabelecimentoDTO;
import br.com.BarbeariaSilvas.dto.DadosFuncionarioDTO;
import br.com.BarbeariaSilvas.model.Estabelecimento;
import br.com.BarbeariaSilvas.repository.EstabelecimentoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstabelecimentoService {

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    @Transactional
    public DadosEstabelecimentoDTO cadastrarEstabelecimento(CadastroEstabelecimentoDTO estabelecimentoDTO) {
        Estabelecimento estabelecimento = new Estabelecimento(estabelecimentoDTO);
        estabelecimentoRepository.save(estabelecimento);
        return new DadosEstabelecimentoDTO(estabelecimento);
    }

    public List<DadosEstabelecimentoDTO> listarEstabelecimento() {
        return estabelecimentoRepository.findAll().stream().map(DadosEstabelecimentoDTO::new).toList();
    }

    public DadosEstabelecimentoDTO listarEstabelecimentoPorId(Long id) {
        var barbearia = estabelecimentoRepository.findById(id);

        if(barbearia.isPresent()){
            return new DadosEstabelecimentoDTO(barbearia.get());
        }
        else {
            throw new ValidationException("Não existe estabelecimento com ID: " + id + " !");
        }
    }

    public  List<DadosFuncionarioDTO> listarFuncionarioPorEstabelecimento(Long id) {
        return estabelecimentoRepository.findById(id).get().getFuncionarios().stream().map(DadosFuncionarioDTO::new).toList();
    }

    @Transactional
    public DadosEstabelecimentoDTO atualizarEstabelecimento(Long id, CadastroEstabelecimentoDTO cadastroEstabelecimentoDTO) {
        var estabelecimento =  estabelecimentoRepository.findById(id);
        if (estabelecimento.isPresent()){
            estabelecimento.get().atualizaBarbeiro(cadastroEstabelecimentoDTO);
            return new DadosEstabelecimentoDTO(estabelecimento.get());
        }
        else {
            throw new ValidationException("Não existe estabelecimento com ID: "+ id + " !");
        }
    }

    @Transactional
    public void deletarEstabelecimento(Long id){
        if(estabelecimentoRepository.existsById(id)){
            estabelecimentoRepository.deleteById(id);
        } else {
            throw new ValidationException("Não existe estabelecimento com ID: " + id + " !");
        }
    }
}

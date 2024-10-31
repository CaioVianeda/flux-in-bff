package br.com.FluxIn.service;

import br.com.FluxIn.dto.CadastroProcedimentoDTO;
import br.com.FluxIn.dto.DadosProcedimentoDTO;
import br.com.FluxIn.model.Procedimento;
import br.com.FluxIn.repository.ProcedimentoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProcedimentoService {

    @Autowired
    ProcedimentoRepository repository;

    @Transactional
    public DadosProcedimentoDTO cadastrarProcedimento(CadastroProcedimentoDTO dto) {
        var procedimento = new Procedimento(dto);
        repository.save(procedimento);
        return new DadosProcedimentoDTO(procedimento);
    }


    public Set<DadosProcedimentoDTO> listarProcedimentos() {
        return repository.findAll().stream().map(DadosProcedimentoDTO::new).collect(Collectors.toSet());
    }

    public DadosProcedimentoDTO listarProcedimentoPorId(Long id) {
        if (repository.existsById(id)){
            return new DadosProcedimentoDTO(repository.getReferenceById(id));
        } else throw new ValidationException("Não existe procedimento com ID : " + id + "!");
    }

    @Transactional
    public DadosProcedimentoDTO atualizarProcedimento(Long id, CadastroProcedimentoDTO dto) {
        var procedimento = repository.findById(id);
        if (procedimento.isPresent()) {
            procedimento.get().atualizarProcedimento(dto);
            return new DadosProcedimentoDTO(procedimento.get());
        } else throw new ValidationException("Não existe procedimento com ID : " + id + "!");
    }

    @Transactional
    public void apagarProcedimento(Long id) {
        var procedimento = repository.findById(id);
        if (procedimento.isPresent()) {
            procedimento.get().toggleAtivo();
        } else throw new ValidationException("Não existe procedimento com ID : " + id + "!");
    }
}

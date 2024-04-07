package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroProcedimentoDTO;
import br.com.BarbeariaSilvas.model.Procedimento;
import br.com.BarbeariaSilvas.repository.ProcedimentoRepository;
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
    public CadastroProcedimentoDTO cadastrarProcedimento(CadastroProcedimentoDTO dto) {
        var procedimento = new Procedimento(dto);
        repository.save(procedimento);
        return dto;
    }


    public Set<CadastroProcedimentoDTO> listarProcedimentos() {
        return repository.findAll().stream().map(CadastroProcedimentoDTO::new).collect(Collectors.toSet());
    }

    @Transactional
    public void apagarProcedimento(Long id) {
        repository.deleteById(id);
    }

}

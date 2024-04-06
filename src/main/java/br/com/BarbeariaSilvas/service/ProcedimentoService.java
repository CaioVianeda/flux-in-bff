package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroProcedimentoDTO;
import br.com.BarbeariaSilvas.model.Procedimento;
import br.com.BarbeariaSilvas.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void apagarProcedimento(Long id) {
        repository.deleteById(id);
    }
}

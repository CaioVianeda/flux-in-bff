package br.com.BarbeariaSilvas.service;

import br.com.BarbeariaSilvas.dto.CadastroProcedimentoDTO;
import br.com.BarbeariaSilvas.model.Procedimento;
import br.com.BarbeariaSilvas.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcedimentoService {

    @Autowired
    ProcedimentoRepository repository;

    public CadastroProcedimentoDTO cadastrarProcedimento(CadastroProcedimentoDTO dto) {
        var procedimento = new Procedimento(dto);
        repository.save(procedimento);

        return dto;
    }


}

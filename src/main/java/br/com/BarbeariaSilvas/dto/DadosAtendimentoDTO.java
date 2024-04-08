package br.com.BarbeariaSilvas.dto;

import br.com.BarbeariaSilvas.model.Atendimento;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosAtendimentoDTO(Long id,
                                  String nomeCliente,
                                  String nomeBarbeiro,
                                  List<CadastroProcedimentoDTO> procedimentos,
                                  BigDecimal total,
                                  LocalDateTime data) {
    public DadosAtendimentoDTO(Atendimento atendimento){
        this(atendimento.getId(), atendimento.getCliente().getNome(),atendimento.getAgenda().getBarbeiro().getNome(),
                atendimento.getProcedimentos().stream().map(CadastroProcedimentoDTO::new).collect(Collectors.toList()),
                    atendimento.getRegistroValorProcedimentos().stream().reduce(BigDecimal.ZERO,BigDecimal::add),
                    atendimento.getData());
    }
}

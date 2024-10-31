package br.com.FluxIn.dto;

import br.com.FluxIn.model.Atendimento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosAtendimentoDTO(Long id,
                                  Long clienteId,
                                  String nomeCliente,
                                  String nomeBarbeiro,
                                  List<DadosProcedimentoDTO> procedimentos,
                                  BigDecimal total,
                                  LocalDateTime data,
                                  LocalTime duracao,
                                  Boolean finalizado,
                                  Boolean confirmado) {
    public DadosAtendimentoDTO(Atendimento atendimento){
        this(atendimento.getId(), atendimento.getCliente().getId() ,atendimento.getCliente().getNome(),atendimento.getFuncionario() .getNome(),
                atendimento.getProcedimentos().stream().map(DadosProcedimentoDTO::new).collect(Collectors.toList()),
                    atendimento.getRegistroValorProcedimentos().stream().reduce(BigDecimal.ZERO,BigDecimal::add),
                    atendimento.getData(), atendimento.getDuracao(), atendimento.getFinalizado(), atendimento.getConfirmado());
    }
}

package com.cooperativa.votacao.dto;

import jakarta.validation.constraints.NotNull;

public class SessaoVotacaoDTO {

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;

    private Integer duracaoMinutos = 1;

    public SessaoVotacaoDTO() {
    }

    public SessaoVotacaoDTO(Long pautaId, Integer duracaoMinutos) {
        this.pautaId = pautaId;
        this.duracaoMinutos = duracaoMinutos;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
}
package com.cooperativa.votacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VotoDTO {

    @NotNull(message = "O ID da pauta é obrigatório")
    private Long pautaId;

    @NotBlank(message = "O CPF do associado é obrigatório")
    private String cpfAssociado;

    @NotBlank(message = "O voto é obrigatório (SIM ou NAO)")
    private String voto;

    public VotoDTO() {
    }

    public VotoDTO(Long pautaId, String cpfAssociado, String voto) {
        this.pautaId = pautaId;
        this.cpfAssociado = cpfAssociado;
        this.voto = voto;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }
}
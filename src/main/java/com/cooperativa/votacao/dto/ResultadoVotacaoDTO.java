package com.cooperativa.votacao.dto;

public class ResultadoVotacaoDTO {

    private Long pautaId;
    private String tituloPauta;
    private int totalVotos;
    private int votosSim;
    private int votosNao;
    private boolean sessaoAberta;
    private String resultado;

    public ResultadoVotacaoDTO() {
    }

    public ResultadoVotacaoDTO(Long pautaId, String tituloPauta, int totalVotos,
                               int votosSim, int votosNao, boolean sessaoAberta, String resultado) {
        this.pautaId = pautaId;
        this.tituloPauta = tituloPauta;
        this.totalVotos = totalVotos;
        this.votosSim = votosSim;
        this.votosNao = votosNao;
        this.sessaoAberta = sessaoAberta;
        this.resultado = resultado;
    }

    public Long getPautaId() {
        return pautaId;
    }

    public void setPautaId(Long pautaId) {
        this.pautaId = pautaId;
    }

    public String getTituloPauta() {
        return tituloPauta;
    }

    public void setTituloPauta(String tituloPauta) {
        this.tituloPauta = tituloPauta;
    }

    public int getTotalVotos() {
        return totalVotos;
    }

    public void setTotalVotos(int totalVotos) {
        this.totalVotos = totalVotos;
    }

    public int getVotosSim() {
        return votosSim;
    }

    public void setVotosSim(int votosSim) {
        this.votosSim = votosSim;
    }

    public int getVotosNao() {
        return votosNao;
    }

    public void setVotosNao(int votosNao) {
        this.votosNao = votosNao;
    }

    public boolean isSessaoAberta() {
        return sessaoAberta;
    }

    public void setSessaoAberta(boolean sessaoAberta) {
        this.sessaoAberta = sessaoAberta;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
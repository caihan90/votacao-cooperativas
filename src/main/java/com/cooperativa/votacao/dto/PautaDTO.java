package com.cooperativa.votacao.dto;

import jakarta.validation.constraints.NotBlank;

public class PautaDTO {

    private Long id;

    @NotBlank(message = "O título da pauta é obrigatório")
    private String titulo;

    private String descricao;

    public PautaDTO() {
    }

    public PautaDTO(Long id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
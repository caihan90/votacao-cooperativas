package com.cooperativa.votacao.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @OneToOne(mappedBy = "pauta", cascade = CascadeType.ALL)
    private SessaoVotacao sessaoVotacao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    private List<Voto> votos = new ArrayList<>();

    public Pauta() {
    }

    public Pauta(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public boolean isSessaoAberta() {
        if (sessaoVotacao == null) {
            return false;
        }

        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(sessaoVotacao.getDataAbertura()) &&
                agora.isBefore(sessaoVotacao.getDataFechamento());
    }

    public int contarVotosSim() {
        return (int) votos.stream()
                .filter(voto -> voto.getVoto() == TipoVoto.SIM)
                .count();
    }

    public int contarVotosNao() {
        return (int) votos.stream()
                .filter(voto -> voto.getVoto() == TipoVoto.NAO)
                .count();
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

    public SessaoVotacao getSessaoVotacao() {
        return sessaoVotacao;
    }

    public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
        this.sessaoVotacao = sessaoVotacao;
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
}
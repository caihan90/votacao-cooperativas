package com.cooperativa.votacao.service;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.dto.ResultadoVotacaoDTO;
import com.cooperativa.votacao.exception.ResourceNotFoundException;
import com.cooperativa.votacao.model.Pauta;
import com.cooperativa.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Transactional
    public Pauta cadastrarPauta(PautaDTO pautaDTO) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(pautaDTO.getTitulo());
        pauta.setDescricao(pautaDTO.getDescricao());

        return pautaRepository.save(pauta);
    }

    public Pauta buscarPautaPorId(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pauta não encontrada com o ID: " + id));
    }

    public ResultadoVotacaoDTO obterResultadoVotacao(Long pautaId) {
        Pauta pauta = buscarPautaPorId(pautaId);

        int votosSim = pauta.contarVotosSim();
        int votosNao = pauta.contarVotosNao();
        int totalVotos = votosSim + votosNao;

        boolean sessaoAberta = pauta.isSessaoAberta();

        String resultado = "Sessão em andamento";
        if (!sessaoAberta && totalVotos > 0) {
            resultado = votosSim > votosNao ? "Aprovada" : "Reprovada";
            if (votosSim == votosNao) {
                resultado = "Empate";
            }
        } else if (!sessaoAberta && totalVotos == 0) {
            resultado = "Não houve votos";
        }

        return new ResultadoVotacaoDTO(
                pauta.getId(),
                pauta.getTitulo(),
                totalVotos,
                votosSim,
                votosNao,
                sessaoAberta,
                resultado
        );
    }
}
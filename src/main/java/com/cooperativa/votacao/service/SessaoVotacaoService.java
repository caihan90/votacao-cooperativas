package com.cooperativa.votacao.service;

import com.cooperativa.votacao.dto.SessaoVotacaoDTO;
import com.cooperativa.votacao.exception.BusinessException;
import com.cooperativa.votacao.model.Pauta;
import com.cooperativa.votacao.model.SessaoVotacao;
import com.cooperativa.votacao.repository.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class SessaoVotacaoService {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;

    @Autowired
    public SessaoVotacaoService(SessaoVotacaoRepository sessaoVotacaoRepository, PautaService pautaService) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
    }

    @Transactional
    public SessaoVotacao abrirSessao(SessaoVotacaoDTO sessaoVotacaoDTO) {
        Pauta pauta = pautaService.buscarPautaPorId(sessaoVotacaoDTO.getPautaId());

        if (pauta.getSessaoVotacao() != null) {
            throw new BusinessException("Já existe uma sessão de votação para esta pauta");
        }

        int duracaoMinutos = sessaoVotacaoDTO.getDuracaoMinutos() != null ?
                sessaoVotacaoDTO.getDuracaoMinutos() : 1;

        LocalDateTime agora = LocalDateTime.now();

        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setPauta(pauta);
        sessaoVotacao.setDataAbertura(agora);
        sessaoVotacao.setDataFechamento(agora.plusMinutes(duracaoMinutos));

        SessaoVotacao sessaoSalva = sessaoVotacaoRepository.save(sessaoVotacao);

        pauta.setSessaoVotacao(sessaoSalva);

        return sessaoSalva;
    }

    public boolean isSessaoAberta(Long pautaId) {
        SessaoVotacao sessao = sessaoVotacaoRepository.findByPautaId(pautaId);

        if (sessao == null) {
            return false;
        }

        return sessao.isAberta();
    }
}
package com.cooperativa.votacao.service;

import com.cooperativa.votacao.dto.VotoDTO;
import com.cooperativa.votacao.exception.BusinessException;
import com.cooperativa.votacao.exception.CpfInvalidoException;
import com.cooperativa.votacao.model.Associado;
import com.cooperativa.votacao.model.Pauta;
import com.cooperativa.votacao.model.TipoVoto;
import com.cooperativa.votacao.model.Voto;
import com.cooperativa.votacao.repository.VotoRepository;
import com.cooperativa.votacao.service.external.CpfValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotoService {

    private final VotoRepository votoRepository;
    private final PautaService pautaService;
    private final AssociadoService associadoService;
    private final SessaoVotacaoService sessaoVotacaoService;
    private final CpfValidatorService cpfValidatorService;

    @Autowired
    public VotoService(
            VotoRepository votoRepository,
            PautaService pautaService,
            AssociadoService associadoService,
            SessaoVotacaoService sessaoVotacaoService,
            CpfValidatorService cpfValidatorService) {
        this.votoRepository = votoRepository;
        this.pautaService = pautaService;
        this.associadoService = associadoService;
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.cpfValidatorService = cpfValidatorService;
    }

    @Transactional
    public Voto registrarVoto(VotoDTO votoDTO) {
        Pauta pauta = pautaService.buscarPautaPorId(votoDTO.getPautaId());

        if (!sessaoVotacaoService.isSessaoAberta(pauta.getId())) {
            throw new BusinessException("A sessão de votação não está aberta para esta pauta");
        }

        boolean ableToVote = cpfValidatorService.cpfAbleToVote(votoDTO.getCpfAssociado());
        if (!ableToVote) {
            throw new BusinessException("Associado não está habilitado para votar nesta sessão");
        }

        Associado associado = associadoService.buscarOuCriarAssociado(votoDTO.getCpfAssociado());

        if (votoRepository.existsByAssociadoIdAndPautaId(associado.getId(), pauta.getId())) {
            throw new BusinessException("Este associado já votou nesta pauta");
        }

        TipoVoto tipoVoto;
        try {
            tipoVoto = TipoVoto.valueOf(votoDTO.getVoto().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Tipo de voto inválido. Use SIM ou NAO");
        }

        Voto voto = new Voto(pauta, associado, tipoVoto);

        pauta.getVotos().add(voto);

        return votoRepository.save(voto);
    }
}
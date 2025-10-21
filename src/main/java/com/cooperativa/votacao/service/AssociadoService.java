package com.cooperativa.votacao.service;

import com.cooperativa.votacao.model.Associado;
import com.cooperativa.votacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    @Autowired
    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Transactional
    public Associado buscarOuCriarAssociado(String cpf) {
        Optional<Associado> associadoOpt = associadoRepository.findByCpf(cpf);

        if (associadoOpt.isPresent()) {
            return associadoOpt.get();
        }

        Associado novoAssociado = new Associado();
        novoAssociado.setCpf(cpf);

        return associadoRepository.save(novoAssociado);
    }
}
package com.cooperativa.votacao.service.external.impl;

import com.cooperativa.votacao.exception.CpfInvalidoException;
import com.cooperativa.votacao.service.external.CpfValidatorService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CpfValidatorServiceImpl implements CpfValidatorService {

    private final Random random = new Random();

    @Override
    public boolean cpfAbleToVote(String cpf) {
        if (random.nextInt(100) < 20) {
            throw new CpfInvalidoException(cpf);
        }

        return random.nextInt(100) < 70;
    }
}
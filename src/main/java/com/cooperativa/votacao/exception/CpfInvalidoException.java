package com.cooperativa.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CpfInvalidoException extends RuntimeException {

    public CpfInvalidoException(String cpf) {
        super("CPF inválido: " + cpf);
    }
}
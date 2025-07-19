package com.raffasdev.cadastroVeiculos.domain.exception;

public class CPFAlreadyExistsException extends RuntimeException {
    public CPFAlreadyExistsException(String cpf) {
        super("CPF já cadastrado: " + cpf);
    }
}

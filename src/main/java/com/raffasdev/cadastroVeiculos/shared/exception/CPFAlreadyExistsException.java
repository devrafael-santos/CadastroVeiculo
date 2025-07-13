package com.raffasdev.cadastroVeiculos.shared.exception;

public class CPFAlreadyExistsException extends RuntimeException {
    public CPFAlreadyExistsException(String cpf) {
        super("CPF já cadastrado: " + cpf);
    }
}

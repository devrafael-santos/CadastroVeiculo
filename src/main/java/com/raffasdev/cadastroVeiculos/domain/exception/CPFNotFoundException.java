package com.raffasdev.cadastroVeiculos.domain.exception;

public class CPFNotFoundException extends RuntimeException {
    public CPFNotFoundException(String message) {
        super("Proprietario não encontrado com o CPF: " + message);
    }
}

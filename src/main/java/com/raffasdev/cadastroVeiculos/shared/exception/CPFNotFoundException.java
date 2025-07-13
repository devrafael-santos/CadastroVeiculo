package com.raffasdev.cadastroVeiculos.shared.exception;

public class CPFNotFoundException extends RuntimeException {
    public CPFNotFoundException(String message) {
        super("Proprietario não encontrado com o CPF: " + message);
    }
}

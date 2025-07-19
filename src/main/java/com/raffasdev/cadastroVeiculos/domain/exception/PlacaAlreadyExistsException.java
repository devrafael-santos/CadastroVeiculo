package com.raffasdev.cadastroVeiculos.domain.exception;

public class PlacaAlreadyExistsException extends RuntimeException {
    public PlacaAlreadyExistsException(String placa) {
        super("Placa já cadastrada: " + placa);
    }
}

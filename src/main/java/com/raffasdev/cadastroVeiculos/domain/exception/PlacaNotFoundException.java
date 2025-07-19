package com.raffasdev.cadastroVeiculos.domain.exception;

public class PlacaNotFoundException extends RuntimeException {
    public PlacaNotFoundException(String placa) {
        super("Placa não encontrada: " + placa);
    }
}

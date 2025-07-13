package com.raffasdev.cadastroVeiculos.util;

import com.raffasdev.cadastroVeiculos.domain.Proprietario;
import com.raffasdev.cadastroVeiculos.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioPostResponse;

public class ProprietarioCreator {

    private final static String CPF = "123.456.789-01";
    private final static String NOME = "John Doe";

    public static Proprietario createValidProprietario() {
        return new Proprietario(
                CPF,
                NOME
        );
    }

    public static ProprietarioPostRequest createProprietarioPostRequest() {
        return new ProprietarioPostRequest(
                CPF,
                NOME
        );
    }

    public static ProprietarioPostResponse createProprietarioPostResponse() {
        return new ProprietarioPostResponse(
                NOME
        );
    }
}

package com.raffasdev.cadastroVeiculos.util;

import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request.ProprietarioPutRequest;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioGetResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioPostResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioPutResponse;

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

    public static ProprietarioGetResponse createProprietarioGetResponse() {
        return new ProprietarioGetResponse(
                CPF,
                NOME
        );
    }

    public static ProprietarioPutRequest createProprietarioPutRequest() {
        return new ProprietarioPutRequest(
                "Jone Doe"
        );
    }

    public static ProprietarioPutResponse createProprietarioPutResponse() {
        return new ProprietarioPutResponse(
                "Jone Doe"
        );
    }
}

package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper;

import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProprietarioMapperTest {

    @Test
    @DisplayName("toPostResponse returns ProprietarioPostResponse")
    void toPostResponse_returnsProprietarioPostResponse() {
        var proprietarioRequired = ProprietarioCreator.createProprietarioPostResponse();
        var proprietarioToBeMapped = ProprietarioCreator.createValidProprietario();

        var proprietarioMapper = new ProprietarioMapper();

        assertEquals(proprietarioRequired, proprietarioMapper.toPostResponse(proprietarioToBeMapped));
    }

    @Test
    @DisplayName("toGetResponse returns ProprietarioGetResponse")
    void toGetResponse_returnsProprietarioGetResponse() {
        var proprietarioRequired = ProprietarioCreator.createProprietarioGetResponse();
        var proprietarioToBeMapped = ProprietarioCreator.createValidProprietario();

        var proprietarioMapper = new ProprietarioMapper();

        assertEquals(proprietarioRequired, proprietarioMapper.toGetResponse(proprietarioToBeMapped));
    }

    @Test
    @DisplayName("toPutResponse returns ProprietarioPutResponse")
    void toPutResponse_returnsProprietarioPutResponse() {
        var proprietarioRequired = ProprietarioCreator.createProprietarioPutResponse();
        var proprietarioToBeMapped = ProprietarioCreator.createValidProprietario();
        proprietarioToBeMapped.setNome("Jone Doe");

        var proprietarioMapper = new ProprietarioMapper();

        assertEquals(proprietarioRequired, proprietarioMapper.toPutResponse(proprietarioToBeMapped));
    }
}
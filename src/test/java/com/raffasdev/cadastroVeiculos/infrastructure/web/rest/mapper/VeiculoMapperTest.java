package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper;

import com.raffasdev.cadastroVeiculos.util.VeiculoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VeiculoMapperTest {

    @Test
    @DisplayName("toPostResponse returns VeiculoPostResponse")
    void toPostResponse_returnsVeiculoPostResponse() {
        var veiculoRequired = VeiculoCreator.createValidPostResponseVeiculo();
        var veiculoToBeMapped = VeiculoCreator.createValidVeiculo();

        var veiculoMapper = new VeiculoMapper();

        assertEquals(veiculoRequired, veiculoMapper.toPostResponse(veiculoToBeMapped));
    }

    @Test
    @DisplayName("toGetResponse returns VeiculoGetResponse")
    void toGetResponse_returnsVeiculoGetResponse() {
        var veiculoRequired = VeiculoCreator.createValidGetResponseVeiculo();
        var veiculoToBeMapped = VeiculoCreator.createValidVeiculo();

        var veiculoMapper = new VeiculoMapper();

        assertEquals(veiculoRequired, veiculoMapper.toGetResponse(veiculoToBeMapped));
    }
}
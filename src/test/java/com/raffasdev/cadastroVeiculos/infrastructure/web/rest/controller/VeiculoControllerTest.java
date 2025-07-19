package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.controller;

import com.raffasdev.cadastroVeiculos.application.service.VeiculoService;
import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper.VeiculoMapper;
import com.raffasdev.cadastroVeiculos.util.VeiculoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VeiculoControllerTest {

    @Mock
    private VeiculoService veiculoServiceMock;

    @Mock
    private VeiculoMapper veiculoMapperMock;

    @InjectMocks
    private VeiculoController veiculoController;

    @Test
    @DisplayName("saveVeiculo returns VeiculoPostResponse when valid data is provided")
    void saveVeiculo_returnsVeiculoPostResponse_WhenValidDataIsProvided() {
        given(veiculoServiceMock.saveVeiculo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .willReturn(VeiculoCreator.createValidVeiculo());

        given(veiculoMapperMock.toPostResponse(ArgumentMatchers.any(Veiculo.class)))
                .willReturn(VeiculoCreator.createValidPostResponseVeiculo());

        var responseEntity = veiculoController.saveVeiculo(VeiculoCreator.createValidPostRequestVeiculo());

        assertNotNull(responseEntity.getBody());
        assertEquals(VeiculoCreator.createValidPostResponseVeiculo(), responseEntity.getBody());
        assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("getVeiculoByPlaca returns VeiculoGetResponse when valid placa is provided")
    void getVeiculoByPlaca_returnsVeiculoGetResponse_WhenValidPlacaIsProvided() {
        given(veiculoServiceMock.getVeiculoByPlaca(ArgumentMatchers.anyString()))
                .willReturn(VeiculoCreator.createValidVeiculo());

        given(veiculoMapperMock.toGetResponse(ArgumentMatchers.any(Veiculo.class)))
                .willReturn(VeiculoCreator.createValidGetResponseVeiculo());

        var responseEntity = veiculoController.getVeiculoByPlaca("ABC1234");

        assertNotNull(responseEntity.getBody());
        assertEquals(VeiculoCreator.createValidGetResponseVeiculo(), responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCode().value());
    }
}
package com.raffasdev.cadastroVeiculos.application.service;

import com.raffasdev.cadastroVeiculos.application.gateways.VeiculoDataGateway;
import com.raffasdev.cadastroVeiculos.domain.exception.CPFNotFoundException;
import com.raffasdev.cadastroVeiculos.domain.exception.PlacaAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.domain.exception.PlacaNotFoundException;
import com.raffasdev.cadastroVeiculos.domain.repository.ProprietarioRepository;
import com.raffasdev.cadastroVeiculos.domain.repository.VeiculoRepository;
import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import com.raffasdev.cadastroVeiculos.util.VeiculoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VeiculoServiceTest {

    @Mock
    private ProprietarioRepository proprietarioRepositoryMock;

    @Mock
    public VeiculoDataGateway veiculoDataGatewayMock;

    @Mock
    private VeiculoRepository veiculoRepositoryMock;

    @InjectMocks
    private VeiculoService veiculoService;

    @Test
    @DisplayName("saveVeiculo returns Veiculo when valid data is provided")
    void saveVeiculo_ReturnsVeiculo_WhenValidDataIsProvided() {

        given(veiculoRepositoryMock.existsByPlaca("ABC1234")).willReturn(false);

        given(proprietarioRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .willReturn(Optional.of(ProprietarioCreator.createValidProprietario()));

        given(veiculoDataGatewayMock.getVeiculoInfosByPlaca(ArgumentMatchers.anyString()))
                .willReturn(VeiculoCreator.createValidVeiculoDataGatewayImplResponse());

        given(veiculoRepositoryMock.save(ArgumentMatchers.any()))
                .willReturn(VeiculoCreator.createValidVeiculo());

        var response = veiculoService.saveVeiculo("ABC1234", "111-111-111-11");

        assertNotNull(response);
        assertEquals(VeiculoCreator.createValidVeiculo(), response);
    }

    @Test
    @DisplayName("saveVeiculo throws PlacaAlreadyExistsException when placa already exists")
    void saveVeiculo_ThrowsPlacaAlreadyExistsException_WhenPlacaAlreadyExists() {

        given(veiculoRepositoryMock.existsByPlaca("ABC1234")).willReturn(true);

        assertThrows(PlacaAlreadyExistsException.class, () -> {
            veiculoService.saveVeiculo("ABC1234", "111-111-111-11");
        });
    }

    @Test
    @DisplayName("saveVeiculo throws CPFNotFoundException when CPF not found")
    void saveVeiculo_ThrowsCPFNotFoundException_WhenCPFNotFound() {

        given(veiculoRepositoryMock.existsByPlaca("ABC1234")).willReturn(false);

        given(proprietarioRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .willReturn(Optional.empty());

        assertThrows(CPFNotFoundException.class, () -> {
            veiculoService.saveVeiculo("ABC1234", "111-111-111-11");
        });
    }

    @Test
    @DisplayName("getVeiculoByPlaca returns Veiculo when placa exists")
    void getVeiculoByPlaca_ReturnsVeiculo_WhenPlacaExists() {

        given(veiculoRepositoryMock.findByPlaca("ABC1234"))
                .willReturn(Optional.of(VeiculoCreator.createValidVeiculo()));

        var response = veiculoService.getVeiculoByPlaca("ABC1234");

        assertNotNull(response);
        assertEquals(VeiculoCreator.createValidVeiculo(), response);
    }

    @Test
    @DisplayName("getVeiculoByPlaca throws PlacaNotFoundException when placa does not exist")
    void getVeiculoByPlaca_ThrowsPlacaNotFoundException_WhenPlacaDoesNotExist() {

        given(veiculoRepositoryMock.findByPlaca("ABC1234"))
                .willReturn(Optional.empty());

        assertThrows(PlacaNotFoundException.class, () -> {
            veiculoService.getVeiculoByPlaca("ABC1234");
        });
    }

}
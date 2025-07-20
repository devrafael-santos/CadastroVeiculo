package com.raffasdev.cadastroVeiculos.infrastructure.persistence.jpa;

import com.raffasdev.cadastroVeiculos.util.VeiculoCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VeiculoJpaRepositoryImplTest {

    @Mock
    private VeiculoJpaRepository jpaRepositoryMock;

    @InjectMocks
    private VeiculoJpaRepositoryImpl veiculoJpaRepositoryImpl;

    @Test
    @DisplayName("existsByPlaca returns true when valid placa is provided")
    void existsByPlaca_ReturnsTrue_WhenValidPlacaIsProvided() {
        var placa = "ABC1234";

        given(jpaRepositoryMock.existsById(placa)).willReturn(true);

        boolean exists = veiculoJpaRepositoryImpl.existsByPlaca(placa);

        assertTrue(exists);
    }

    @Test
    @DisplayName("findByPlaca returns Veiculo when valid placa is provided")
    void findByPlaca_ReturnsVeiculo_WhenValidPlacaIsProvided() {
        var veiculo = VeiculoCreator.createValidVeiculo();

        given(jpaRepositoryMock.findById(veiculo.getPlaca())).willReturn(Optional.of(veiculo));

        var foundVeiculo = veiculoJpaRepositoryImpl.findByPlaca(veiculo.getPlaca());

        assertTrue(foundVeiculo.isPresent());
        assertEquals(veiculo.getPlaca(), foundVeiculo.get().getPlaca());
    }

    @Test
    @DisplayName("save returns Veiculo when valid Veiculo is provided")
    void save_ReturnsVeiculo_WhenValidVeiculoIsProvided() {
        var veiculo = VeiculoCreator.createValidVeiculo();

        given(jpaRepositoryMock.save(veiculo)).willReturn(veiculo);

        var savedVeiculo = veiculoJpaRepositoryImpl.save(veiculo);

        assertNotNull(savedVeiculo);
        assertEquals(veiculo.getPlaca(), savedVeiculo.getPlaca());
    }

}
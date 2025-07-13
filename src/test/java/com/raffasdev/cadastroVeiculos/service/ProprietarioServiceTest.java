package com.raffasdev.cadastroVeiculos.service;

import com.raffasdev.cadastroVeiculos.repository.ProprietarioRepository;
import com.raffasdev.cadastroVeiculos.shared.exception.CPFAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
class ProprietarioServiceTest {

    @Mock
    private ProprietarioRepository proprietarioRepositoryMock;

    @InjectMocks
    private ProprietarioService proprietarioService;

    @Test
    @DisplayName("saveProprietario returns ProprietarioPostResponse when valid data is provided")
    void saveProprietario_ReturnsProprietarioPostResponse_WhenValidDataIsProvided() {
        given(proprietarioRepositoryMock.existsByCpf(ArgumentMatchers.anyString()))
                .willReturn(Boolean.FALSE);

        var request = ProprietarioCreator.createProprietarioPostRequest();
        var response = proprietarioService.saveProprietario(request);

        Assertions.assertThatCode(() -> proprietarioService.saveProprietario(request))
                .doesNotThrowAnyException();
        assertNotNull(response);
        assertEquals(request.getNome(), response.nome());
    }

    @Test
    @DisplayName("saveProprietario throws CPFAlreadyExistsException when a existing CPF is provided")
    void saveProprietario_ThrowsCPFAlreadyExistsException_WhenExistingCPFIsProvided() {

        given(proprietarioRepositoryMock.existsByCpf(ArgumentMatchers.anyString()))
                .willReturn(Boolean.TRUE);

        assertThrows(CPFAlreadyExistsException.class, () -> {
            proprietarioService.saveProprietario(
                    ProprietarioCreator.createProprietarioPostRequest());
        });
    }

}
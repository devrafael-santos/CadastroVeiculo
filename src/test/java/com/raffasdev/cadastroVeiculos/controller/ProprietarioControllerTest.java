package com.raffasdev.cadastroVeiculos.controller;

import com.raffasdev.cadastroVeiculos.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.service.ProprietarioService;
import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class ProprietarioControllerTest {

    @Mock
    private ProprietarioService proprietarioService;

    @InjectMocks
    private ProprietarioController proprietarioController;

    @Test
    @DisplayName("saveProprietario returns ProprietarioPostResponse when valid data is provided")
    void saveProprietario_returnsProprietarioPostResponse_WhenValidDataIsProvided() {
        BDDMockito.given(proprietarioService.saveProprietario(ArgumentMatchers.any(ProprietarioPostRequest.class)))
                .willReturn(ProprietarioCreator.createProprietarioPostResponse());

        var requestEntity = ProprietarioCreator.createProprietarioPostRequest();

        var responseEntity = proprietarioController.saveProprietario(requestEntity);

        assertNotNull(responseEntity.getBody());
        assertEquals(requestEntity.getNome(), responseEntity.getBody().nome());
        assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("getProprietarioByCpf returns ProprietarioGetResponse when CPF exists")
    void getProprietarioByCpf_returnsProprietarioGetResponse_WhenCPFExists() {
        BDDMockito.given(proprietarioService.getProprietarioByCpf(ArgumentMatchers.anyString()))
                .willReturn(ProprietarioCreator.createProprietarioGetResponse());

        String cpf = ProprietarioCreator.createValidProprietario().getCpf();

        var responseEntity = proprietarioController.getProprietarioByCpf(cpf);
        System.out.println(cpf + " " + responseEntity.getBody());
        assertNotNull(responseEntity.getBody());
        assertEquals(cpf, responseEntity.getBody().cpf());
        assertEquals(200, responseEntity.getStatusCode().value());
    }
}
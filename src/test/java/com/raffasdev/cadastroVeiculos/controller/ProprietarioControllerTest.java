package com.raffasdev.cadastroVeiculos.controller;

import com.raffasdev.cadastroVeiculos.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioGetResponse;
import com.raffasdev.cadastroVeiculos.service.ProprietarioService;
import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ProprietarioControllerTest {

    @Mock
    private ProprietarioService proprietarioService;

    @InjectMocks
    private ProprietarioController proprietarioController;

    @Test
    @DisplayName("saveProprietario returns ProprietarioPostResponse when valid data is provided")
    void saveProprietario_returnsProprietarioPostResponse_WhenValidDataIsProvided() {
        given(proprietarioService.saveProprietario(ArgumentMatchers.any(ProprietarioPostRequest.class)))
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
        given(proprietarioService.getProprietarioByCpf(ArgumentMatchers.anyString()))
                .willReturn(ProprietarioCreator.createProprietarioGetResponse());

        String cpf = ProprietarioCreator.createValidProprietario().getCpf();

        var responseEntity = proprietarioController.getProprietarioByCpf(cpf);
        assertNotNull(responseEntity.getBody());
        assertEquals(cpf, responseEntity.getBody().cpf());
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("getProprietarios returns page of ProprietarioGetResponse when having data")
    void getProprietarios_returnsPageOfProprietarioGetResponse_WhenHavingData() {
        var proprietario = ProprietarioCreator.createProprietarioGetResponse();
        var proprietario2 = ProprietarioCreator.createProprietarioGetResponse();

        Page<ProprietarioGetResponse> proprietarioPage = new PageImpl<>(List.of(proprietario, proprietario2));

        given(proprietarioService.getProprietarios(ArgumentMatchers.any(Pageable.class)))
                .willReturn(proprietarioPage);


        var responseEntity = proprietarioController.getProprietarios(Pageable.unpaged());

        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().getContent().size());
        assertEquals(200, responseEntity.getStatusCode().value());
    }
}
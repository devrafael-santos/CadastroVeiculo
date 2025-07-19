package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.controller;

import com.raffasdev.cadastroVeiculos.application.service.ProprietarioService;
import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper.ProprietarioMapper;
import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProprietarioControllerTest {

    @Mock
    private ProprietarioService proprietarioServiceMock;

    @Mock
    private ProprietarioMapper proprietarioMapperMock;

    @InjectMocks
    private ProprietarioController proprietarioController;

    @Test
    @DisplayName("saveProprietario returns ProprietarioPostResponse when valid data is provided")
    void saveProprietario_returnsProprietarioPostResponse_WhenValidDataIsProvided() {
        given(proprietarioServiceMock.saveProprietario(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .willReturn(ProprietarioCreator.createValidProprietario());

        given(proprietarioMapperMock.toPostResponse(ArgumentMatchers.any(Proprietario.class)))
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
        given(proprietarioServiceMock.getProprietarioByCpf(ArgumentMatchers.anyString()))
                .willReturn(ProprietarioCreator.createValidProprietario());

        given(proprietarioMapperMock.toGetResponse(ArgumentMatchers.any(Proprietario.class)))
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
        var proprietario = ProprietarioCreator.createValidProprietario();
        var proprietario2 = ProprietarioCreator.createValidProprietario();

        given(proprietarioMapperMock.toGetResponse(ArgumentMatchers.any(Proprietario.class))).willReturn(
                ProprietarioCreator.createProprietarioGetResponse()
        );

        Page<Proprietario> proprietarioPage = new PageImpl<>(List.of(proprietario, proprietario2));

        given(proprietarioServiceMock.getProprietarios(ArgumentMatchers.any(Pageable.class)))
                .willReturn(proprietarioPage);


        var responseEntity = proprietarioController.getProprietarios(Pageable.unpaged());

        assertNotNull(responseEntity.getBody());
        assertEquals(2, responseEntity.getBody().getContent().size());
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("updateProprietario returns ProprietarioPutResponse when valid data is provided")
    void updateProprietario_returnsProprietarioPutResponse_WhenValidDataIsProvided() {
        given(proprietarioServiceMock.updateProprietario(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()))
                .willReturn(ProprietarioCreator.createValidProprietario());

        given(proprietarioMapperMock.toPutResponse(ArgumentMatchers.any(Proprietario.class)))
                .willReturn(ProprietarioCreator.createProprietarioPutResponse());

        var cpf = "123.456.789-01";

        var requestEntity = ProprietarioCreator.createProprietarioPutRequest();

        var responseEntity = proprietarioController.updateProprietario(requestEntity, cpf);

        assertNotNull(responseEntity.getBody());
        assertEquals(requestEntity.getNome(), responseEntity.getBody().nome());
        assertEquals(200, responseEntity.getStatusCode().value());
    }

    @Test
    @DisplayName("deleteProprietarioByCpf deletes Proprietario when CPF exists")
    void deleteProprietarioByCpf_deletesProprietario_WhenCPFExists() {
        var cpf = "123.456.789-01";

        var responseEntity = proprietarioController.deleteProprietarioByCpf(cpf);

        assertDoesNotThrow(() -> proprietarioServiceMock.deleteProprietarioByCpf(cpf));

        assertNull(responseEntity.getBody());
        assertEquals(204, responseEntity.getStatusCode().value());
    }
}
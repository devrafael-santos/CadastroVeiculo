package com.raffasdev.cadastroVeiculos.application.service;

import com.raffasdev.cadastroVeiculos.domain.exception.CPFAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.domain.exception.CPFNotFoundException;
import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.domain.repository.ProprietarioRepository;
import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import org.assertj.core.api.Assertions;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
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

        given(proprietarioRepositoryMock.save(ArgumentMatchers.any(Proprietario.class)))
                .willReturn(ProprietarioCreator.createValidProprietario());

        var request = ProprietarioCreator.createProprietarioPostRequest();
        var response = proprietarioService.saveProprietario(request.getCpf(), request.getNome());

        Assertions.assertThatCode(() -> proprietarioService.saveProprietario(request.getCpf(), request.getNome()))
                .doesNotThrowAnyException();

        assertNotNull(response);
        assertEquals(request.getNome(), response.getNome());
    }

    @Test
    @DisplayName("saveProprietario throws CPFAlreadyExistsException when a existing CPF is provided")
    void saveProprietario_ThrowsCPFAlreadyExistsException_WhenExistingCPFIsProvided() {

        given(proprietarioRepositoryMock.existsByCpf(ArgumentMatchers.anyString()))
                .willReturn(Boolean.TRUE);

        var proprietario = ProprietarioCreator.createValidProprietario();

        assertThrows(CPFAlreadyExistsException.class, () -> {
            proprietarioService.saveProprietario(
                    proprietario.getCpf(), proprietario.getNome());
        });
    }

    @Test
    @DisplayName("getProprietarioByCpf returns ProprietarioGetResponse when CPF exists")
    void getProprietarioByCpf_ReturnsProprietarioGetResponse_WhenCPFExists() {
        var proprietario = ProprietarioCreator.createValidProprietario();

        given(proprietarioRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .willReturn(Optional.of(proprietario));

        var response = proprietarioService.getProprietarioByCpf(proprietario.getCpf());

        assertNotNull(response);
        assertEquals(proprietario.getNome(), response.getNome());
        assertEquals(proprietario.getCpf(), response.getCpf());
    }

    @Test
    @DisplayName("getProprietarioByCpf throws CPFNotFoundException when CPF not exists")
    void getProprietarioByCpf_ThrowsCPFNotFoundException_WhenCPFNotExists() {
        var proprietario = ProprietarioCreator.createValidProprietario();

        given(proprietarioRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .willReturn(Optional.empty());

        assertThrows(CPFNotFoundException.class, () -> {
            proprietarioService.getProprietarioByCpf(proprietario.getCpf());
        });

        verify(proprietarioRepositoryMock, never()).save(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("getProprietarios returns a page of ProprietarioGetResponse when having data")
    void getProprietarios_ReturnsPageOfProprietariosGetResponse_WhenHavingData() {
        var proprietario = ProprietarioCreator.createValidProprietario();
        var proprietario2 = ProprietarioCreator.createValidProprietario();
        proprietario2.setCpf("111.111.111-11");
        proprietario2.setNome("Jona Doe");

        Page<Proprietario> proprietarioPage = new PageImpl<>(List.of(proprietario, proprietario2));

        given(proprietarioRepositoryMock.findAll(ArgumentMatchers.any(Pageable.class)))
                .willReturn(proprietarioPage);

        Page<Proprietario> responsePage = proprietarioService.getProprietarios(Pageable.unpaged());
        assertNotNull(responsePage);
        assertFalse(responsePage.isEmpty());
        assertEquals(2, responsePage.getTotalElements());
    }

    @Test
    @DisplayName("getProprietarios returns an empty page of ProprietarioGetResponse when having data")
    void getProprietarios_ReturnsEmptyPageOfProprietariosGetResponse_WhenNoDataIsPresent() {

        Page<Proprietario> emptyProprietarioPage = new PageImpl<>(List.of());

        given(proprietarioRepositoryMock.findAll(ArgumentMatchers.any(Pageable.class)))
                .willReturn(emptyProprietarioPage);

        Page<Proprietario> responsePage = proprietarioService.getProprietarios(Pageable.unpaged());
        assertNotNull(responsePage);
        assertTrue(responsePage.isEmpty());
        assertEquals(0, responsePage.getTotalElements());
    }

    @Test
    @DisplayName("updateProprietario throws CPFAlreadyExistsException when a existing CPF is provided")
    void updateProprietario_ThrowsCPFNotFoundException_WhenValidDataIsProvided() {
        given(proprietarioRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .willReturn(Optional.of(ProprietarioCreator.createValidProprietario()));

        var cpf = "123.456.789-01";

        given(proprietarioRepositoryMock.save(ArgumentMatchers.any(Proprietario.class)))
                .willReturn(new Proprietario(
                        cpf,
                        "Jone Doe"
                ));


        var request = ProprietarioCreator.createProprietarioPutRequest();
        var response = proprietarioService.updateProprietario(request.getNome(), cpf);

        assertNotNull(response);
        assertEquals(request.getNome(), response.getNome());
    }

    @Test
    @DisplayName("updateProprietario throws CPFNotFoundException when CPF not exists")
    void updateProprietario_ThrowsCPFNotFoundException_WhenCPFnotExists() {
        var proprietario = ProprietarioCreator.createValidProprietario();

        given(proprietarioRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .willReturn(Optional.empty());

        assertThrows(CPFNotFoundException.class, () -> {
            proprietarioService.updateProprietario(proprietario.getNome(),
                    proprietario.getCpf());
        });

        verify(proprietarioRepositoryMock, never()).save(ArgumentMatchers.any(Proprietario.class));
    }

    @Test
    @DisplayName("deleteProprietarioByCpf deletes Proprietario when CPF exists")
    void deleteProprietarioByCpf_deletesProprietario_WhenCPFExists() {
        assertDoesNotThrow(() -> {
            given(proprietarioRepositoryMock.existsByCpf(ArgumentMatchers.anyString()))
                    .willReturn(Boolean.TRUE);
            proprietarioService.deleteProprietarioByCpf("123.456.789-01");
        });
    }

    @Test
    @DisplayName("deleteProprietarioByCpf throws CPFNotFoundException when CPF not exists")
    void deleteProprietarioByCpf_throwsCPFNotFoundException_WhenCPFNotExists() {

        given(proprietarioRepositoryMock.existsByCpf(ArgumentMatchers.anyString()))
                .willReturn(Boolean.FALSE);

        assertThrows(CPFNotFoundException.class, () -> {
            proprietarioService.deleteProprietarioByCpf("123.456.789-01");
        });
        verify(proprietarioRepositoryMock, never()).save(ArgumentMatchers.any(Proprietario.class));
    }

}
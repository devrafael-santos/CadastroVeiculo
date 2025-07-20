package com.raffasdev.cadastroVeiculos.infrastructure.persistence.jpa;

import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.util.ProprietarioCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProprietarioRepositoryImplTest {

    @Mock
    private ProprietarioJpaRepository jpaRepositoryMock;

    @InjectMocks
    private ProprietarioRepositoryImpl proprietarioRepositoryImpl;

    @Test
    @DisplayName("existsByCpf returns True when valid CPF is provided")
    void existsByCpf_ReturnsTrue_WhenValidCpfIsProvided() {
        var cpf = "111-111-111-11";

        given(jpaRepositoryMock.existsByCpf(cpf)).willReturn(true);

        boolean exists = proprietarioRepositoryImpl.existsByCpf(cpf);

        assertTrue(exists);
    }

    @Test
    @DisplayName("findByCpf returns Proprietario when valid CPF is provided")
    void findByCpf_ReturnsProprietario_WhenValidCpfIsProvided() {
        var cpf = "111-111-111-11";

        given(jpaRepositoryMock.findByCpf(cpf)).willReturn(Optional.of(new Proprietario()));

        var proprietario = proprietarioRepositoryImpl.findByCpf(cpf);

        assertTrue(proprietario.isPresent());
    }

    @Test
    @DisplayName("findAll returns Page of Proprietario")
    void findAll_ReturnsPageOfProprietario() {
        var pageable = PageRequest.of(0, 10);
        given(jpaRepositoryMock.findAll(pageable)).willReturn(Page.empty());

        var proprietariosPage = proprietarioRepositoryImpl.findAll(pageable);

        assertNotNull(proprietariosPage);
        assertTrue(proprietariosPage.isEmpty());
    }

    @Test
    @DisplayName("deleteByCpf deletes Proprietario by CPF")
    void deleteByCpf_DeletesProprietarioByCpf() {
        var cpf = "111-111-111-11";

        proprietarioRepositoryImpl.deleteByCpf(cpf);

        given(jpaRepositoryMock.existsByCpf(cpf)).willReturn(false);
        assertFalse(jpaRepositoryMock.existsByCpf(cpf));
    }

    @Test
    @DisplayName("save saves Proprietario and returns it")
    void save_SavesProprietario_AndReturnsIt() {
        var proprietario = ProprietarioCreator.createValidProprietario();

        given(jpaRepositoryMock.save(proprietario)).willReturn(proprietario);

        var savedProprietario = proprietarioRepositoryImpl.save(proprietario);

        assertNotNull(savedProprietario);
        assertEquals(proprietario, savedProprietario);
    }
}
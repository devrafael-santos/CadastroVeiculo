package com.raffasdev.cadastroVeiculos.application.service;

import com.raffasdev.cadastroVeiculos.domain.exception.CPFAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.domain.exception.CPFNotFoundException;
import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.domain.repository.ProprietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    @Transactional
    public Proprietario saveProprietario(String cpf, String nome) {
        if (proprietarioRepository.existsByCpf(cpf)) {
            throw new CPFAlreadyExistsException(cpf);
        }

        Proprietario proprietario = new Proprietario(cpf, nome);

        return proprietarioRepository.save(proprietario);
    }

    public Proprietario getProprietarioByCpf(String cpf) {
        return proprietarioRepository.findByCpf(cpf).orElseThrow(
                () -> new CPFNotFoundException(cpf));
    }

    public Page<Proprietario> getProprietarios(Pageable pageable) {
        return proprietarioRepository.findAll(pageable);
    }

    @Transactional
    public Proprietario updateProprietario(String nome, String cpf) {
        Proprietario proprietario = proprietarioRepository.findByCpf(cpf).orElseThrow(
                () -> new CPFNotFoundException(cpf));
        proprietario.setNome(nome);

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void deleteProprietarioByCpf(String cpf) {
        if (!proprietarioRepository.existsByCpf(cpf)) {
            throw new CPFNotFoundException(cpf);
        }

        proprietarioRepository.deleteByCpf(cpf);
    }
}

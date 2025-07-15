package com.raffasdev.cadastroVeiculos.service;

import com.raffasdev.cadastroVeiculos.domain.Proprietario;
import com.raffasdev.cadastroVeiculos.repository.ProprietarioRepository;
import com.raffasdev.cadastroVeiculos.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioGetResponse;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioPostResponse;
import com.raffasdev.cadastroVeiculos.rest.mapper.ProprietarioMapper;
import com.raffasdev.cadastroVeiculos.shared.exception.CPFAlreadyExistsException;
import com.raffasdev.cadastroVeiculos.shared.exception.CPFNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    @Transactional
    public ProprietarioPostResponse saveProprietario(ProprietarioPostRequest requestProprietario) {
        if (proprietarioRepository.existsByCpf(requestProprietario.getCpf())) {
            throw new CPFAlreadyExistsException(requestProprietario.getCpf());
        }

        Proprietario proprietario = new Proprietario(requestProprietario.getCpf(), requestProprietario.getNome());
        proprietarioRepository.save(proprietario);
        return ProprietarioMapper.toPostResponse(proprietario);
    }

    public ProprietarioGetResponse getProprietarioByCpf(String cpf) {
        Proprietario proprietario = Optional.ofNullable(proprietarioRepository.findByCpf(cpf)).orElseThrow(
                () -> new CPFNotFoundException(cpf));
        return ProprietarioMapper.toGetResponse(proprietario);
    }

    public Page<ProprietarioGetResponse> getProprietarios(Pageable pageable) {
        return proprietarioRepository.findAll(pageable).map(ProprietarioMapper::toGetResponse);
    }
}

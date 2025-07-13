package com.raffasdev.cadastroVeiculos.service;

import com.raffasdev.cadastroVeiculos.domain.Proprietario;
import com.raffasdev.cadastroVeiculos.repository.ProprietarioRepository;
import com.raffasdev.cadastroVeiculos.rest.dto.request.ProprietarioPostRequest;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioPostResponse;
import com.raffasdev.cadastroVeiculos.rest.mapper.ProprietarioMapper;
import com.raffasdev.cadastroVeiculos.shared.exception.CPFAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return ProprietarioMapper.toResponse(proprietario);
    }
}

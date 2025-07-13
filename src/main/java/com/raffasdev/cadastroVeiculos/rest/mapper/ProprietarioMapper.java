package com.raffasdev.cadastroVeiculos.rest.mapper;

import com.raffasdev.cadastroVeiculos.domain.Proprietario;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioPostResponse;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioMapper {

    public static ProprietarioPostResponse toResponse(Proprietario proprietario) {
        return new ProprietarioPostResponse(proprietario.getNome());
    }
}

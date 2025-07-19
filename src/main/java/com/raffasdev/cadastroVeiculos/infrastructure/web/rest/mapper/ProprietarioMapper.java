package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.mapper;

import com.raffasdev.cadastroVeiculos.domain.model.Proprietario;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioGetResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioPostResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.ProprietarioPutResponse;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioMapper {

    public ProprietarioPostResponse toPostResponse(Proprietario proprietario) {
        return new ProprietarioPostResponse(proprietario.getNome());
    }

    public ProprietarioGetResponse toGetResponse(Proprietario proprietario) {
        return new ProprietarioGetResponse(proprietario.getCpf(), proprietario.getNome());
    }

    public ProprietarioPutResponse toPutResponse(Proprietario proprietario) {
        return new ProprietarioPutResponse(proprietario.getNome());
    }

}

package com.raffasdev.cadastroVeiculos.rest.mapper;

import com.raffasdev.cadastroVeiculos.domain.Proprietario;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioGetResponse;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioPostResponse;
import com.raffasdev.cadastroVeiculos.rest.dto.response.ProprietarioPutResponse;
import org.springframework.stereotype.Component;

@Component
public class ProprietarioMapper {

    public static ProprietarioPostResponse toPostResponse(Proprietario proprietario) {
        return new ProprietarioPostResponse(proprietario.getNome());
    }

    public static ProprietarioGetResponse toGetResponse(Proprietario proprietario) {
        return new ProprietarioGetResponse(proprietario.getCpf(), proprietario.getNome());
    }

    public static ProprietarioPutResponse toPutResponse(Proprietario proprietario) {
        return new ProprietarioPutResponse(proprietario.getNome());
    }

}

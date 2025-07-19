package com.raffasdev.cadastroVeiculos.util;

import com.raffasdev.cadastroVeiculos.domain.model.Veiculo;
import com.raffasdev.cadastroVeiculos.infrastructure.gateways.http.dto.response.VeiculoDataGatewayResponse;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request.VeiculoPostRequest;
import com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.VeiculoPostResponse;

public class VeiculoCreator {

    public static VeiculoDataGatewayResponse createValidVeiculoDataGatewayImplResponse() {
        return new VeiculoDataGatewayResponse(
                "Dodge",
                "Omni",
                "WBAPT73598C125222",
                true);
    }

    public static Veiculo createValidVeiculo() {
        return new Veiculo(
                "ABC1234",
                ProprietarioCreator.createValidProprietario(),
                "Dodge",
                "Omni",
                "WBAPT73598C125222",
                true);
    }

    public static VeiculoPostRequest createValidPostRequestVeiculo() {
        return new VeiculoPostRequest(
                "ABC1234",
                ProprietarioCreator.createValidProprietario().getCpf()
        );
    }

    public static VeiculoPostResponse createValidPostResponseVeiculo() {
        return new VeiculoPostResponse(
                "ABC1234",
                ProprietarioCreator.createValidProprietario().getCpf(),
                "Dodge",
                "Omni",
                "WBAPT73598C125222",
                true);
    }
}

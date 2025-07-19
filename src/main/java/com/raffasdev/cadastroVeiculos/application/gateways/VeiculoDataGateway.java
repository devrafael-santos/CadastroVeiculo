package com.raffasdev.cadastroVeiculos.application.gateways;

import com.raffasdev.cadastroVeiculos.infrastructure.gateways.http.dto.response.VeiculoDataGatewayResponse;

public interface VeiculoDataGateway {
    public VeiculoDataGatewayResponse getVeiculoInfosByPlaca(String placa);
}

package com.raffasdev.cadastroVeiculos.infrastructure.gateways.http.dto.response;

public record VeiculoDataGatewayResponse(String marca, String modelo, String chassi, boolean licenciado) {
}

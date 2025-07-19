package com.raffasdev.cadastroVeiculos.infrastructure.gateways.http;

import com.raffasdev.cadastroVeiculos.application.gateways.VeiculoDataGateway;
import com.raffasdev.cadastroVeiculos.infrastructure.gateways.http.dto.response.VeiculoDataGatewayResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@AllArgsConstructor
public class VeiculoDataGatewayImpl implements VeiculoDataGateway {

    private final RestClient restClient;

    @Override
    public VeiculoDataGatewayResponse getVeiculoInfosByPlaca(String placa) {
        return restClient.get()
                .uri(String.format("/veiculos?key=55ad1cd0&placa=%s", placa))
                .retrieve()
                .body(VeiculoDataGatewayResponse.class);
    }


}

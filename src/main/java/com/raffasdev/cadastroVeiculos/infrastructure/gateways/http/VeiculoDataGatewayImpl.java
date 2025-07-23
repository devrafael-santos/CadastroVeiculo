package com.raffasdev.cadastroVeiculos.infrastructure.gateways.http;

import com.raffasdev.cadastroVeiculos.application.gateways.VeiculoDataGateway;
import com.raffasdev.cadastroVeiculos.domain.exception.ServiceUnavailableException;
import com.raffasdev.cadastroVeiculos.infrastructure.gateways.http.dto.response.VeiculoDataGatewayResponse;
import lombok.AllArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
@AllArgsConstructor
public class VeiculoDataGatewayImpl implements VeiculoDataGateway {

    private final RestClient restClient;

    @Override
    @Retryable(
            retryFor = {RestClientException.class},
            maxAttempts = 2,
            backoff = @Backoff(delay = 500)
    )
    public VeiculoDataGatewayResponse getVeiculoInfosByPlaca(String placa) {
        return restClient.get()
                .uri(String.format("/veiculos?key=55ad1cd0&placa=%s", placa))
                .retrieve()
                .body(VeiculoDataGatewayResponse.class);
    }

    @Recover
    public void recover(RestClientException e, String url) {
        System.err.println("RECOVER: Todas as tentativas falharam para: " + url + " - " + e.getMessage());
        throw new ServiceUnavailableException(url);
    }
}

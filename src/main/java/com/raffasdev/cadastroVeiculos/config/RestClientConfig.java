package com.raffasdev.cadastroVeiculos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                // /veiculos?key=55ad1cd0&placa=ABC1234
                .baseUrl("https://my.api.mockaroo.com")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}

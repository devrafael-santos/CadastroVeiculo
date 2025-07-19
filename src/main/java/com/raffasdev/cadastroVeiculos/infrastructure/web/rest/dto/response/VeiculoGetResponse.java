package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response;

public record VeiculoGetResponse(String placa, String cpf, String marca, String modelo, String chassi,
                                 boolean licenciado) {
}

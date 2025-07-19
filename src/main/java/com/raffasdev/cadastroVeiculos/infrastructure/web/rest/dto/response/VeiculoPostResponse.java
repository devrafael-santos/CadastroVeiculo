package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response;

public record VeiculoPostResponse(String placa, String cpf, String marca, String modelo, String chassi,
                                  boolean licenciado) {
}

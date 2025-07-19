package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VeiculoPostRequest {

    @NotBlank(message = "placa do veículo é obrigatória.")
    private String placa;

    @NotBlank(message = "CPF do cliente é obrigatório.")
    @jakarta.validation.constraints.Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$",
            message = "CPF deve estar no formato XXX.XXX.XXX-XX [números o lugar de X].")
    private String cpf;
}

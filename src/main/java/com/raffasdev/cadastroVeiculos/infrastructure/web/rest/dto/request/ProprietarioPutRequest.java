package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProprietarioPutRequest {

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
}

package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProprietarioPostRequest {

    @NotBlank(message = "CPF é obrigatório.")
    @jakarta.validation.constraints.Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$",
            message = "CPF deve estar no formato XXX.XXX.XXX-XX [números o lugar de X].")
    private String cpf;

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;
}

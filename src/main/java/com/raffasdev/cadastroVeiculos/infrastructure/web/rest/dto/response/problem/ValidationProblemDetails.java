package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.problem;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationProblemDetails extends ProblemDetails {
    private final String fields;
    private final String fieldsMessage;
}
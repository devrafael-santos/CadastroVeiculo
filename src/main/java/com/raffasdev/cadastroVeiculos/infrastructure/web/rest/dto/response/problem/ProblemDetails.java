package com.raffasdev.cadastroVeiculos.infrastructure.web.rest.dto.response.problem;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
public class ProblemDetails {
    private String title;
    private int status;
    private String details;
    private Instant timestamp;
}

package com.raffasdev.cadastroVeiculos.domain.exception;

public class ServiceUnavailableException extends RuntimeException {
  public ServiceUnavailableException(String url) {
    super("Todas as tentativas para o recurso: " + url + "falharam. O serviço está indisponível no momento.");
  }
}

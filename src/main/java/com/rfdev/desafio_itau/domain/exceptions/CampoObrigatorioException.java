package com.rfdev.desafio_itau.domain.exceptions;

public class CampoObrigatorioException extends RuntimeException {

  public CampoObrigatorioException(String texto) {
    super(texto);
  }

}

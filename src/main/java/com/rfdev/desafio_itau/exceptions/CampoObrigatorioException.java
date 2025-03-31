package com.rfdev.desafio_itau.exceptions;

public class CampoObrigatorioException extends RuntimeException {

  public CampoObrigatorioException(String texto) {
    super(texto);
  }

}

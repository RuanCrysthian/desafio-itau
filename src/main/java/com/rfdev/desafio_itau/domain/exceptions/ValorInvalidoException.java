package com.rfdev.desafio_itau.domain.exceptions;

public class ValorInvalidoException extends RuntimeException {

  public ValorInvalidoException(String texto) {
    super(texto);
  }

}

package com.rfdev.desafio_itau.domain.exceptions;

public class DataHoraInvalidaException extends RuntimeException {

  public DataHoraInvalidaException(String texto) {
    super(texto);
  }

}

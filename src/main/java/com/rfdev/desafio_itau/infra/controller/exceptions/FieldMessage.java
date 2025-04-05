package com.rfdev.desafio_itau.infra.controller.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

  private final String fieldName;

  private final String message;

  public FieldMessage(String fieldName, String message) {
    this.fieldName = fieldName;
    this.message = message;
  }
}

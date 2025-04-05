package com.rfdev.desafio_itau.infra.controller.exceptions;

import com.rfdev.desafio_itau.domain.exceptions.CampoObrigatorioException;
import com.rfdev.desafio_itau.domain.exceptions.DataHoraInvalidaException;
import com.rfdev.desafio_itau.domain.exceptions.ValorInvalidoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CampoObrigatorioException.class)
  public ResponseEntity<StandardError> validation(CampoObrigatorioException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    return ResponseEntity.status(status).build();
  }

  @ExceptionHandler(DataHoraInvalidaException.class)
  public ResponseEntity<StandardError> validation(DataHoraInvalidaException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    return ResponseEntity.status(status).build();
  }

  @ExceptionHandler(ValorInvalidoException.class)
  public ResponseEntity<StandardError> validation(ValorInvalidoException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
    return ResponseEntity.status(status).build();
  }

}

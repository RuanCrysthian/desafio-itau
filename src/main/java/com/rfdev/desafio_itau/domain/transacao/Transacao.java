package com.rfdev.desafio_itau.domain.transacao;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.rfdev.desafio_itau.domain.exceptions.CampoObrigatorioException;
import com.rfdev.desafio_itau.domain.exceptions.DataHoraInvalidaException;
import com.rfdev.desafio_itau.domain.exceptions.ValorInvalidoException;

public class Transacao {
  private BigDecimal valor;
  private OffsetDateTime dataHora;

  private Transacao(BigDecimal valor, OffsetDateTime dataHora) {
    this.valor = valor;
    this.dataHora = dataHora;
    validate();
  }

  public static Transacao create(BigDecimal valor, OffsetDateTime dataHora) {
    return new Transacao(valor, dataHora);
  }

  private void validate() {
    if (valor == null) {
      throw new CampoObrigatorioException("valor é obrigatório");
    }
    if (dataHora == null) {
      throw new CampoObrigatorioException("dataHora é obrigatório");
    }
    if (dataHora.isAfter(OffsetDateTime.now())) {
      throw new DataHoraInvalidaException("dataHora não pode ser no futuro");
    }
    if (valor.compareTo(BigDecimal.ZERO) <= 0) {
      throw new ValorInvalidoException("valor deve ser maior que zero");
    }
  }

  public BigDecimal getValor() {
    return valor;
  }

  public OffsetDateTime getDataHora() {
    return dataHora;
  }
}

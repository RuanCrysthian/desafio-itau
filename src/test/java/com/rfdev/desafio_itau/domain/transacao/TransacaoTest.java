package com.rfdev.desafio_itau.domain.transacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.rfdev.desafio_itau.exceptions.CampoObrigatorioException;
import com.rfdev.desafio_itau.exceptions.DataHoraInvalidaException;
import com.rfdev.desafio_itau.exceptions.ValorInvalidoException;

public class TransacaoTest {

  @Test
  void deveCriarTransacao() {
    BigDecimal valor = BigDecimal.valueOf(100);
    OffsetDateTime dataHora = OffsetDateTime.now();
    Transacao transacao = Transacao.create(valor, dataHora);
    assertNotNull(transacao);
    assertEquals(valor, transacao.getValor());
    assertEquals(dataHora, transacao.getDataHora());
  }

  @Test
  void deveEnviarExcecaoQuandoValorForNull() {
    Assertions.assertThrows(CampoObrigatorioException.class, () -> {
      Transacao.create(null, OffsetDateTime.now());
    });
  }

  @Test
  void deveEnviarExcecaoQuandoDataHoraForNull() {
    Assertions.assertThrows(CampoObrigatorioException.class, () -> {
      Transacao.create(BigDecimal.valueOf(100), null);
    });
  }

  @Test
  void deveEnviarExcecaoQuandoDataHoraForNoFuturo() {
    Assertions.assertThrows(DataHoraInvalidaException.class, () -> {
      Transacao.create(BigDecimal.valueOf(100), OffsetDateTime.now().plusSeconds(1));
    });
  }

  @Test
  void deveEnviarExcecaoQuandoValorForIgualZero() {
    Assertions.assertThrows(ValorInvalidoException.class, () -> {
      Transacao.create(BigDecimal.ZERO, OffsetDateTime.now());
    });
  }

  @Test
  void deveEnviarExcecaoQuandoValorForMenorQueZero() {
    Assertions.assertThrows(ValorInvalidoException.class, () -> {
      Transacao.create(BigDecimal.valueOf(-1), OffsetDateTime.now());
    });
  }
}

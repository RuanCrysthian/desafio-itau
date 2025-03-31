package com.rfdev.desafio_itau.infra.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rfdev.desafio_itau.domain.transacao.Transacao;

public class TransacaoRepositoryEmMemoriaTest {

  private TransacaoRepositoryEmMemoria repository;
  private Transacao transacao;

  @BeforeEach
  void setUp() {
    repository = new TransacaoRepositoryEmMemoria();
    transacao = Transacao.create(
        BigDecimal.valueOf(100.0),
        OffsetDateTime.now());
  }

  @Test
  void deveArmazenarTransacoes() {
    repository.criar(transacao);
    Assertions.assertEquals(1, repository.quantidadeTransacoes());
  }

  @Test
  void deveLimparTransacoes() {
    repository.criar(transacao);
    Assertions.assertEquals(1, repository.quantidadeTransacoes());
    repository.limpar();
    Assertions.assertEquals(0, repository.quantidadeTransacoes());
  }

  @Test
  void deveRetornarListaDeTransacoes() {
    repository.criar(transacao);
    Assertions.assertEquals(1, repository.quantidadeTransacoes());
    Transacao transacao2 = Transacao.create(
        BigDecimal.valueOf(200.0),
        OffsetDateTime.now());
    repository.criar(transacao2);
    Assertions.assertEquals(2, repository.quantidadeTransacoes());
    Assertions.assertEquals(transacao, repository.getTransacoes().get(0));
    Assertions.assertEquals(transacao2, repository.getTransacoes().get(1));
  }
}

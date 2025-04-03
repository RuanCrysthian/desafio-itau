package com.rfdev.desafio_itau.application.usecase.transacao;

import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;

public class CriarTransacaoUseCaseTest {

  @Mock
  private TransacaoRepository transacaoRepository;

  @InjectMocks
  private CriarTransacaoUseCase criarTransacaoUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveCriarUmaTransacao() {
    CriarTransacaoInputDto input = new CriarTransacaoInputDto(
        BigDecimal.valueOf(100),
        OffsetDateTime.now());

    CriarTransacaoOutputDto output = criarTransacaoUseCase.executar(input);

    Assertions.assertEquals(new CriarTransacaoOutputDto(), output);
    Mockito.verify(transacaoRepository).criar(Mockito.any());
    Mockito.verify(transacaoRepository, times(1)).criar(Mockito.any());
  }
}

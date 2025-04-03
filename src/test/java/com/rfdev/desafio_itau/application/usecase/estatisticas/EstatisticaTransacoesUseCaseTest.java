package com.rfdev.desafio_itau.application.usecase.estatisticas;

import com.rfdev.desafio_itau.domain.transacao.Transacao;
import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EstatisticaTransacoesUseCaseTest {

  @Mock
  private TransacaoRepository transacaoRepository;

  @InjectMocks
  private EstatisticaTransacoesUseCase estatisticaTransacoesUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveRetornarZeroNosCamposDeEstatisticasQuandoNaoExistirTransacoes() {
    when(transacaoRepository.getTransacoes()).thenReturn(Collections.emptyList());
    EstatisticaTransacoesInputDto inputDto = new EstatisticaTransacoesInputDto();

    EstatisticaTransacoesOutputDto outputDto = estatisticaTransacoesUseCase.execute(inputDto);

    assertEquals(0L, outputDto.count());
    assertEquals(0.00, outputDto.sum());
    assertEquals(0.00, outputDto.avg());
    assertEquals(0.00, outputDto.min());
    assertEquals(0.00, outputDto.max());
  }

  @Test
  void deveRetornarEstatisticasCorretasQuandoExistirTransacoes() {
    List<Transacao> transacoes = List.of(
      Transacao.create(BigDecimal.valueOf(10.123), OffsetDateTime.now().minusSeconds(30)),
      Transacao.create(BigDecimal.valueOf(20.456), OffsetDateTime.now().minusSeconds(30)),
      Transacao.create(BigDecimal.valueOf(30.789), OffsetDateTime.now().minusSeconds(30))
    );
    when(transacaoRepository.getTransacoes()).thenReturn(transacoes);
    EstatisticaTransacoesInputDto inputDto = new EstatisticaTransacoesInputDto();

    EstatisticaTransacoesOutputDto outputDto = estatisticaTransacoesUseCase.execute(inputDto);

    assertEquals(3L, outputDto.count());
    assertEquals(61.37, outputDto.sum());  // 10.123 + 20.456 + 30.789 = 61.368 → 61.37
    assertEquals(20.46, outputDto.avg());  // 61.368 / 3 = 20.456 → 20.46
    assertEquals(10.12, outputDto.min());
    assertEquals(30.79, outputDto.max());
  }

  @Test
  void deveRetornarEstatisticasCorretasQuandoExistirTransacoesAnterioresAoLimiteDe60Segundos() {
    List<Transacao> transacoes = List.of(
      Transacao.create(BigDecimal.valueOf(10.555), OffsetDateTime.now().minusSeconds(30)),
      Transacao.create(BigDecimal.valueOf(20.111), OffsetDateTime.now().minusSeconds(30)),
      Transacao.create(BigDecimal.valueOf(30.999), OffsetDateTime.now().minusSeconds(30)),
      Transacao.create(BigDecimal.valueOf(100.0), OffsetDateTime.now().minusSeconds(70)),
      Transacao.create(BigDecimal.valueOf(40.0), OffsetDateTime.now().minusSeconds(61))
    );
    when(transacaoRepository.getTransacoes()).thenReturn(transacoes);
    EstatisticaTransacoesInputDto inputDto = new EstatisticaTransacoesInputDto();

    EstatisticaTransacoesOutputDto outputDto = estatisticaTransacoesUseCase.execute(inputDto);

    assertEquals(3L, outputDto.count());
    assertEquals(61.67, outputDto.sum());  // 10.555 + 20.111 + 30.999 = 61.665 → 61.67
    assertEquals(20.56, outputDto.avg());  // 61.665 / 3 = 20.555 → 20.56
    assertEquals(10.56, outputDto.min());  // 10.555 → 10.56
    assertEquals(31.00, outputDto.max());  // 30.999 → 31.00
  }

  @Test
  void deveArredondarCorretamenteValoresComMaisDeDuasCasasDecimais() {
    List<Transacao> transacoes = List.of(
      Transacao.create(new BigDecimal("1.004"), OffsetDateTime.now().minusSeconds(30)),
      Transacao.create(new BigDecimal("1.005"), OffsetDateTime.now().minusSeconds(30)),
      Transacao.create(new BigDecimal("1.006"), OffsetDateTime.now().minusSeconds(30))
    );
    when(transacaoRepository.getTransacoes()).thenReturn(transacoes);
    EstatisticaTransacoesInputDto inputDto = new EstatisticaTransacoesInputDto();

    EstatisticaTransacoesOutputDto outputDto = estatisticaTransacoesUseCase.execute(inputDto);

    assertEquals(3L, outputDto.count());
    assertEquals(3.01, outputDto.sum());
    assertEquals(1.01, outputDto.avg());
    assertEquals(1.00, outputDto.min());
    assertEquals(1.01, outputDto.max());
  }
}


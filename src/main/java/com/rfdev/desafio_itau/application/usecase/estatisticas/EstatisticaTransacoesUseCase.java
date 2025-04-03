package com.rfdev.desafio_itau.application.usecase.estatisticas;

import com.rfdev.desafio_itau.domain.transacao.Transacao;
import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class EstatisticaTransacoesUseCase {

  private static final int SEGUNDOS_PARA_FILTRAR = 60;
  private static final int CASAS_DECIMAIS = 2;

  private final TransacaoRepository transacaoRepository;

  public EstatisticaTransacoesUseCase(TransacaoRepository transacaoRepository) {
    this.transacaoRepository = transacaoRepository;
  }

  public EstatisticaTransacoesOutputDto execute(EstatisticaTransacoesInputDto inputDto) {
    OffsetDateTime limite = OffsetDateTime.now().minusSeconds(SEGUNDOS_PARA_FILTRAR);
    List<Transacao> transacoes = transacaoRepository.getTransacoes();

    DoubleSummaryStatistics estatisticas = calcularEstatisticas(transacoes, limite);

    return construirDto(estatisticas);
  }

  private DoubleSummaryStatistics calcularEstatisticas(List<Transacao> transacoes, OffsetDateTime limite) {
    return transacoes.stream()
      .filter(transacao -> transacao.getDataHora().isAfter(limite))
      .mapToDouble(transacao -> transacao.getValor().doubleValue())
      .summaryStatistics();
  }

  private EstatisticaTransacoesOutputDto construirDto(DoubleSummaryStatistics estatisticas) {
    long count = estatisticas.getCount();
    double sum = arredondar(estatisticas.getSum());
    double avg = count > 0 ? arredondar(estatisticas.getAverage()) : 0.0;
    double min = count > 0 ? arredondar(estatisticas.getMin()) : 0.0;
    double max = count > 0 ? arredondar(estatisticas.getMax()) : 0.0;

    return new EstatisticaTransacoesOutputDto(count, sum, avg, min, max);
  }

  private double arredondar(double valor) {
    return new BigDecimal(String.valueOf(valor))
      .setScale(CASAS_DECIMAIS, RoundingMode.HALF_UP)
      .doubleValue();
  }
}

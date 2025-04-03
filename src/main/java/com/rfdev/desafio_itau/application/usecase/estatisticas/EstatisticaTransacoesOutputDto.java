package com.rfdev.desafio_itau.application.usecase.estatisticas;

import java.math.BigDecimal;
import java.math.BigInteger;

public record EstatisticaTransacoesOutputDto(
    Long count,
    Double sum,
    Double avg,
    Double min,
    Double max) {
}

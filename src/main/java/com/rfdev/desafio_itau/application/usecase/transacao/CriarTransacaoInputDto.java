package com.rfdev.desafio_itau.application.usecase.transacao;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CriarTransacaoInputDto(
    BigDecimal valor,
    OffsetDateTime dataHora) {

}

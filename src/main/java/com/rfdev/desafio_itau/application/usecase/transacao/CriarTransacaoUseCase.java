package com.rfdev.desafio_itau.application.usecase.transacao;

import com.rfdev.desafio_itau.domain.transacao.Transacao;
import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class CriarTransacaoUseCase {

  private final TransacaoRepository transacaoRepository;
  private final Logger logger = LoggerFactory.getLogger(CriarTransacaoUseCase.class);

  public CriarTransacaoUseCase(TransacaoRepository transacaoRepository) {
    this.transacaoRepository = transacaoRepository;
  }

  public CriarTransacaoOutputDto executar(CriarTransacaoInputDto input) {
    Transacao transacao = Transacao.create(input.valor(), input.dataHora());
    transacaoRepository.criar(transacao);
    logger.info("Transacao no valor {}, data {}, criada em {}", input.valor(), input.dataHora(), OffsetDateTime.now());
    return new CriarTransacaoOutputDto();
  }
}

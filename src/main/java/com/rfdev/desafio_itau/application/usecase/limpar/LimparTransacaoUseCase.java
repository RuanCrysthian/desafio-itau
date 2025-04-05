package com.rfdev.desafio_itau.application.usecase.limpar;

import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class LimparTransacaoUseCase {

  private final TransacaoRepository transacaoRepository;
  private final Logger logger = LoggerFactory.getLogger(LimparTransacaoUseCase.class);

  public LimparTransacaoUseCase(TransacaoRepository transacaoRepository) {
    this.transacaoRepository = transacaoRepository;
  }

  public LimparTransacaoOuputDto executar(LimparTransacaoInputDto input) {
    logger.info("transacoes excluidas em {}", OffsetDateTime.now());
    transacaoRepository.limpar();

    return new LimparTransacaoOuputDto();
  }

}

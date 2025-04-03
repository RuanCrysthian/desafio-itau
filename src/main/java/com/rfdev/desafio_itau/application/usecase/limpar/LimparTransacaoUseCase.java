package com.rfdev.desafio_itau.application.usecase.limpar;

import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;

public class LimparTransacaoUseCase {

  private final TransacaoRepository transacaoRepository;

  public LimparTransacaoUseCase(TransacaoRepository transacaoRepository) {
    this.transacaoRepository = transacaoRepository;
  }

  public LimparTransacaoOuputDto executar(LimparTransacaoInputDto input) {
    transacaoRepository.limpar();
    return new LimparTransacaoOuputDto();
  }

}

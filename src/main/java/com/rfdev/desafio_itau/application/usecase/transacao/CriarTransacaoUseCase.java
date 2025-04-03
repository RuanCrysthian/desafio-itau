package com.rfdev.desafio_itau.application.usecase.transacao;

import com.rfdev.desafio_itau.domain.transacao.Transacao;
import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;

public class CriarTransacaoUseCase {

  private final TransacaoRepository transacaoRepository;

  public CriarTransacaoUseCase(TransacaoRepository transacaoRepository) {
    this.transacaoRepository = transacaoRepository;
  }

  public CriarTransacaoOutputDto executar(CriarTransacaoInputDto input) {
    Transacao transacao = Transacao.create(input.valor(), input.dataHora());
    transacaoRepository.criar(transacao);
    return new CriarTransacaoOutputDto();
  }
}

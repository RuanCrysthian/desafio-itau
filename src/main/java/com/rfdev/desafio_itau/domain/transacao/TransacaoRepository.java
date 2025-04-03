package com.rfdev.desafio_itau.domain.transacao;

import java.util.List;

public interface TransacaoRepository {
  void criar(Transacao transacao);

  void limpar();

  int quantidadeTransacoes();

  List<Transacao> getTransacoes();
}

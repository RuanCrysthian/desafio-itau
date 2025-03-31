package com.rfdev.desafio_itau.infra.repository;

import java.util.ArrayList;
import java.util.List;

import com.rfdev.desafio_itau.domain.transacao.Transacao;
import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;

public class TransacaoRepositoryEmMemoria implements TransacaoRepository {

  private final List<Transacao> transacoes = new ArrayList<>();

  @Override
  public void criar(Transacao transacao) {
    transacoes.add(transacao);
  }

  @Override
  public void limpar() {
    transacoes.clear();
  }

  @Override
  public int quantidadeTransacoes() {
    return transacoes.size();
  }

  @Override
  public List<Transacao> getTransacoes() {
    return transacoes;
  }
}

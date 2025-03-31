package com.rfdev.desafio_itau.application.usecase.limpar;

import static org.mockito.Mockito.times;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;

public class LimparTransacaoUseCaseTest {

  @Mock
  private TransacaoRepository transacaoRepository;

  @InjectMocks
  private LimparTransacaoUseCase deleteTransacaoUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void deveDeletarAsTransacoes() {
    LimparTransacaoInputDto input = new LimparTransacaoInputDto();
    Mockito.when(transacaoRepository.quantidadeTransacoes()).thenReturn(0);

    LimparTransacaoOuputDto output = deleteTransacaoUseCase.executar(input);

    Assertions.assertEquals(new LimparTransacaoOuputDto(), output);
    Assertions.assertEquals(transacaoRepository.quantidadeTransacoes(), 0);
    Assertions.assertEquals(new ArrayList<>(), transacaoRepository.getTransacoes());
    Mockito.verify(transacaoRepository).limpar();
    Mockito.verify(transacaoRepository, times(1)).limpar();
  }

}

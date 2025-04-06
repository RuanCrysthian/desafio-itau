package com.rfdev.desafio_itau.infra.controller;

import com.rfdev.desafio_itau.application.usecase.estatisticas.EstatisticaTransacoesInputDto;
import com.rfdev.desafio_itau.application.usecase.estatisticas.EstatisticaTransacoesOutputDto;
import com.rfdev.desafio_itau.application.usecase.estatisticas.EstatisticaTransacoesUseCase;
import com.rfdev.desafio_itau.application.usecase.limpar.LimparTransacaoInputDto;
import com.rfdev.desafio_itau.application.usecase.limpar.LimparTransacaoOuputDto;
import com.rfdev.desafio_itau.application.usecase.limpar.LimparTransacaoUseCase;
import com.rfdev.desafio_itau.application.usecase.transacao.CriarTransacaoInputDto;
import com.rfdev.desafio_itau.application.usecase.transacao.CriarTransacaoOutputDto;
import com.rfdev.desafio_itau.application.usecase.transacao.CriarTransacaoUseCase;
import com.rfdev.desafio_itau.domain.transacao.TransacaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransacaoController {
  private final LimparTransacaoUseCase limparTransacaoUseCase;
  private final CriarTransacaoUseCase criarTransacaoUseCase;
  private final EstatisticaTransacoesUseCase estatisticaTransacoesUseCase;

  public TransacaoController(TransacaoRepository transacaoRepository) {
    limparTransacaoUseCase = new LimparTransacaoUseCase(transacaoRepository);
    criarTransacaoUseCase = new CriarTransacaoUseCase(transacaoRepository);
    estatisticaTransacoesUseCase = new EstatisticaTransacoesUseCase(transacaoRepository);
  }

  @Operation(description = "Criar uma transação.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Criar uma transação com sucesso"),
    @ApiResponse(responseCode = "400", description = "Requisição inválida"),
    @ApiResponse(responseCode = "422", description = "Erro de validação nos dados de entrada"),
  })
  @PostMapping("/transacao")
  public ResponseEntity<CriarTransacaoOutputDto> criarTransacao(@RequestBody CriarTransacaoInputDto input) {
    CriarTransacaoOutputDto output = criarTransacaoUseCase.executar(input);
    return ResponseEntity.status(HttpStatus.CREATED).body(output);
  }

  @Operation(description = "Limpar todas as transações.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Limpar todas as transações com sucesso")
  })
  @DeleteMapping("/transacao")
  public ResponseEntity<LimparTransacaoOuputDto> limparTransacao() {
    LimparTransacaoOuputDto output = limparTransacaoUseCase.executar(new LimparTransacaoInputDto());
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(output);
  }

  @Operation(description = "Consultar estatísticas das transações em um espaço de tempo de 60 segundos.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso"),
  })
  @GetMapping("/estatistica")
  public ResponseEntity<EstatisticaTransacoesOutputDto> estatisticaTransacoes() {
    EstatisticaTransacoesOutputDto output = estatisticaTransacoesUseCase.execute(new EstatisticaTransacoesInputDto());
    return ResponseEntity.ok(output);
  }
}

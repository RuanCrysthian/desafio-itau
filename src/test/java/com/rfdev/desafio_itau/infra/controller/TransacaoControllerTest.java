package com.rfdev.desafio_itau.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rfdev.desafio_itau.application.usecase.transacao.CriarTransacaoInputDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TransacaoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void deveCriarUmaTransacaoCorretamenteEREtornarStatusCreated() throws Exception {
    BigDecimal valor = new BigDecimal("123.45");
    OffsetDateTime dataHora = OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00");
    CriarTransacaoInputDto input = new CriarTransacaoInputDto(valor, dataHora);

    mockMvc.perform(post("/transacao")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(input)))
      .andExpect(status().isCreated());
  }

  @Test
  void deveEnviarUnprocessableEntityQuandoValorForInvalido() throws Exception {
    BigDecimal valor = new BigDecimal("-123.45");
    OffsetDateTime dataHora = OffsetDateTime.parse("2020-08-07T12:34:56.789-03:00");
    CriarTransacaoInputDto input = new CriarTransacaoInputDto(valor, dataHora);

    mockMvc.perform(post("/transacao")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(input)))
      .andExpect(status().isUnprocessableEntity());
  }

  @Test
  void deveEnviarUnprocessableEntityQuandoDataForInvalido() throws Exception {
    BigDecimal valor = new BigDecimal("123.45");
    String dataHoraInvalida = "data-invalida";
    String input = String.format("{\"valor\": %s, \"dataHora\": \"%s\"}", valor, dataHoraInvalida);

    mockMvc.perform(post("/transacao")
        .contentType(MediaType.APPLICATION_JSON)
        .content(input))
      .andExpect(status().isBadRequest());
  }

  @Test
  void deveExcluirTransacoesERetornarNoContent() throws Exception {
    mockMvc.perform(delete("/transacao"))
      .andExpect(status().isNoContent());
  }

  @Test
  void deveRetornarEstatisticasComStatus200() throws Exception {
    mockMvc.perform(get("/estatistica"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.count").value(0))
      .andExpect(jsonPath("$.sum").value(0.0))
      .andExpect(jsonPath("$.avg").value(0.0))
      .andExpect(jsonPath("$.min").value(0.0))
      .andExpect(jsonPath("$.max").value(0.0));
  }


}
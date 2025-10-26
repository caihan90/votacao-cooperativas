package com.cooperativa.votacao.controller;

import com.cooperativa.votacao.dto.SessaoVotacaoDTO;
import com.cooperativa.votacao.model.SessaoVotacao;
import com.cooperativa.votacao.service.SessaoVotacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sessoes")
@Tag(name = "Sessões de Votação", description = "Endpoints para gerenciamento de sessões de votação")
public class SessaoVotacaoController {

    private final SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    public SessaoVotacaoController(SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @PostMapping
    @Operation(
            summary = "Abre uma nova sessão de votação",
            description = "Abre uma sessão de votação para uma pauta específica, com duração configurável (padrão: 1 minuto)",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Sessão aberta com sucesso",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos ou pauta inexistente"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Já existe uma sessão aberta para esta pauta"
                    )
            }
    )
    public ResponseEntity<Map<String, Object>> abrirSessao(@Valid @RequestBody SessaoVotacaoDTO sessaoVotacaoDTO) {
        SessaoVotacao sessao = sessaoVotacaoService.abrirSessao(sessaoVotacaoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("id", sessao.getId());
        resposta.put("pautaId", sessao.getPauta().getId());
        resposta.put("pautaTitulo", sessao.getPauta().getTitulo());
        resposta.put("dataAbertura", sessao.getDataAbertura().format(formatter));
        resposta.put("dataFechamento", sessao.getDataFechamento().format(formatter));
        resposta.put("duracaoMinutos",
                sessaoVotacaoDTO.getDuracaoMinutos() != null ?
                        sessaoVotacaoDTO.getDuracaoMinutos() : 1);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }
}
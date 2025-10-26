package com.cooperativa.votacao.controller;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.dto.ResultadoVotacaoDTO;
import com.cooperativa.votacao.model.Pauta;
import com.cooperativa.votacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pautas")
@Tag(name = "Pautas", description = "Endpoints para gerenciamento de pautas")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    @Operation(
            summary = "Cadastra uma nova pauta",
            description = "Cria uma nova pauta para votação com título e descrição",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Pauta criada com sucesso",
                            content = @Content(schema = @Schema(implementation = PautaDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados da pauta inválidos"
                    )
            }
    )
    public ResponseEntity<PautaDTO> cadastrarPauta(@Valid @RequestBody PautaDTO pautaDTO) {
        Pauta pauta = pautaService.cadastrarPauta(pautaDTO);

        PautaDTO resposta = new PautaDTO();
        resposta.setId(pauta.getId());
        resposta.setTitulo(pauta.getTitulo());
        resposta.setDescricao(pauta.getDescricao());

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca uma pauta pelo ID",
            description = "Retorna os detalhes de uma pauta específica",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pauta encontrada com sucesso",
                            content = @Content(schema = @Schema(implementation = PautaDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pauta não encontrada"
                    )
            }
    )
    public ResponseEntity<PautaDTO> buscarPauta(@PathVariable Long id) {
        Pauta pauta = pautaService.buscarPautaPorId(id);

        PautaDTO resposta = new PautaDTO();
        resposta.setId(pauta.getId());
        resposta.setTitulo(pauta.getTitulo());
        resposta.setDescricao(pauta.getDescricao());

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}/resultado")
    @Operation(
            summary = "Obtém o resultado da votação",
            description = "Retorna a contagem de votos e o resultado da votação para uma pauta",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Resultado obtido com sucesso",
                            content = @Content(schema = @Schema(implementation = ResultadoVotacaoDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pauta não encontrada"
                    )
            }
    )
    public ResponseEntity<ResultadoVotacaoDTO> obterResultadoVotacao(@PathVariable Long id) {
        ResultadoVotacaoDTO resultado = pautaService.obterResultadoVotacao(id);
        return ResponseEntity.ok(resultado);
    }
}

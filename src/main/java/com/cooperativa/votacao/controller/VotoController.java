package com.cooperativa.votacao.controller;

import com.cooperativa.votacao.dto.VotoDTO;
import com.cooperativa.votacao.exception.CpfInvalidoException;
import com.cooperativa.votacao.model.Voto;
import com.cooperativa.votacao.service.VotoService;
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
@RequestMapping("/api/votos")
@Tag(name = "Votos", description = "Endpoints para registro e gerenciamento de votos")
public class VotoController {

    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    @Operation(
            summary = "Registra um voto",
            description = "Registra o voto (SIM ou NAO) de um associado em uma pauta com sessão aberta. " +
                    "Cada associado pode votar apenas uma vez por pauta. " +
                    "O CPF é validado por um serviço externo para determinar se o associado pode votar.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Voto registrado com sucesso",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos, sessão fechada, associado já votou ou não está habilitado para votar"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "CPF inválido ou pauta não encontrada"
                    )
            }
    )
    public ResponseEntity<Map<String, Object>> registrarVoto(@Valid @RequestBody VotoDTO votoDTO) {
        Voto voto = votoService.registrarVoto(votoDTO);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("id", voto.getId());
        resposta.put("pautaId", voto.getPauta().getId());
        resposta.put("pautaTitulo", voto.getPauta().getTitulo());
        resposta.put("cpfAssociado", voto.getAssociado().getCpf());
        resposta.put("voto", voto.getVoto().toString());
        resposta.put("dataHora", voto.getDataHora().format(formatter));

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }
}
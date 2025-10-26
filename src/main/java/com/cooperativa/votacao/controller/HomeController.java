package com.cooperativa.votacao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>API de Votação para Cooperativas</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            line-height: 1.6;
                            margin: 0;
                            padding: 20px;
                            color: #333;
                        }
                        .container {
                            max-width: 800px;
                            margin: 0 auto;
                        }
                        h1 {
                            color: #2c3e50;
                            border-bottom: 1px solid #eee;
                            padding-bottom: 10px;
                        }
                        h2 {
                            color: #3498db;
                        }
                        ul {
                            padding-left: 20px;
                        }
                        li {
                            margin-bottom: 10px;
                        }
                        a {
                            color: #3498db;
                            text-decoration: none;
                        }
                        a:hover {
                            text-decoration: underline;
                        }
                        .endpoint {
                            background-color: #f8f9fa;
                            padding: 10px;
                            border-radius: 4px;
                            margin-bottom: 5px;
                            font-family: monospace;
                        }
                        .card {
                            border: 1px solid #ddd;
                            border-radius: 4px;
                            padding: 15px;
                            margin-bottom: 20px;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>API de Votação para Cooperativas</h1>
                        
                        <div class="card">
                            <h2>Bem-vindo(a) à API de Votação!</h2>
                            <p>Esta API permite gerenciar sessões de votação em assembleias de cooperativas.</p>
                            <p>Principais funcionalidades:</p>
                            <ul>
                                <li>Cadastro e gerenciamento de pautas</li>
                                <li>Abertura de sessões de votação</li>
                                <li>Registro de votos por associados</li>
                                <li>Consulta de resultados de votações</li>
                            </ul>
                        </div>
                        
                        <div class="card">
                            <h2>Endpoints Principais:</h2>
                            <ul>
                                <li>
                                    <strong>Pautas:</strong>
                                    <div class="endpoint">GET /api/pautas</div>
                                    <div class="endpoint">POST /api/pautas</div>
                                    <div class="endpoint">GET /api/pautas/{id}</div>
                                    <div class="endpoint">GET /api/pautas/{id}/resultado</div>
                                </li>
                                <li>
                                    <strong>Sessões de Votação:</strong>
                                    <div class="endpoint">POST /api/sessoes</div>
                                </li>
                                <li>
                                    <strong>Votos:</strong>
                                    <div class="endpoint">POST /api/votos</div>
                                </li>
                            </ul>
                        </div>
                        
                        <div class="card">
                            <h2>Links Úteis:</h2>
                            <ul>
                                <li><a href="/swagger-ui.html">Documentação Swagger (API)</a></li>
                                <li><a href="/h2-console">Console do Banco de Dados H2</a> (se configurado)</li>
                            </ul>
                        </div>
                    </div>
                </body>
                </html>
                """;
    }
}
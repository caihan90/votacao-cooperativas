# Sistema de Votação para Cooperativas

API REST para gerenciar sessões de votação em assembleias de cooperativas.

## Objetivo

Permitir que cooperativas realizem votações de forma digital, com registro de pautas, abertura de sessões e contabilização de votos.

## Tecnologias

- Java 17
- Spring Boot 3.1.5
- PostgreSQL
- Spring Data JPA
- Maven
- Swagger/OpenAPI
- Lombok

## Funcionalidades

- Cadastro de pautas para votação
- Abertura de sessões com duração configurável
- Registro de votos por CPF
- Validação para evitar votos duplicados
- Contabilização automática de resultados

## Regras de Negócio

- Cada associado pode votar apenas uma vez por sessão
- Sessões têm duração padrão de 1 minuto (configurável)
- Votos só são aceitos durante a sessão aberta
- CPF do associado é validado antes do voto
- Resultado calculado após encerramento da sessão
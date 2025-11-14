# Sistema de Votação para Cooperativas

API REST para gerenciar sessões de votação em assembleias de cooperativas. Permite cadastrar pautas, abrir sessões com duração configurável e registrar votos dos associados.

## Tecnologias

- Java 17
- Spring Boot 3.1.5
- PostgreSQL
- Spring Data JPA
- Maven
- Swagger/OpenAPI
- Lombok

## Requisitos

- JDK 17
- Maven 3.6+
- PostgreSQL 12+

## Instalação

### PostgreSQL

**Linux (Ubuntu/Debian):**

```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
```

**Windows:**

Baixe o instalador em https://www.postgresql.org/download/windows/ e defina a senha durante a instalação.

**macOS:**

```bash
brew install postgresql
brew services start postgresql
```

### Configuração do Banco de Dados

Acesse o PostgreSQL:

```bash
sudo -u postgres psql
```

Execute os comandos:

```sql
CREATE DATABASE votacao_db;
ALTER USER postgres WITH PASSWORD 'postgres';
\q
```

### Configuração da Aplicação

Edite `src/main/resources/application.properties` com suas credenciais:

```properties
spring.datasource.username=postgres
spring.datasource.password=sua_senha
```

## Execução

Clone o repositório:

```bash
git clone https://github.com/caihan90/votacao-cooperativas.git
cd votacao-cooperativas
```

Compile e execute:

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## Documentação da API

Swagger UI: `http://localhost:8080/swagger-ui.html`

## Endpoints

### Pautas

- `POST /pautas` - Criar pauta
- `GET /pautas` - Listar pautas
- `GET /pautas/{id}` - Buscar pauta
- `GET /pautas/{id}/resultado` - Resultado da votação

### Sessões

- `POST /sessoes` - Abrir sessão
- `GET /sessoes/{id}` - Consultar sessão

### Votos

- `POST /votos` - Registrar voto

Exemplo de requisição:

```json
{
  "pautaId": 1,
  "cpfAssociado": "12345678900",
  "voto": "SIM"
}
```

## Estrutura do Projeto

```
src/main/java/com/cooperativa/votacao/
├── config/          - Configurações
├── controller/      - Controllers REST
├── dto/             - Data Transfer Objects
├── exception/       - Tratamento de exceções
├── model/           - Entidades JPA
├── repository/      - Repositórios
└── service/         - Lógica de negócio
```

## Regras de Negócio

- Cada associado vota uma vez por sessão
- Sessões têm duração configurável (padrão: 1 minuto)
- Votos aceitos apenas em sessões abertas
- CPF validado antes do registro
- Resultado contabilizado após encerramento da sessão

## Observações para Windows + WSL

Se estiver usando WSL no Windows com IntelliJ:

1. Instale o PostgreSQL dentro do WSL, não no Windows
2. Instale Java 17 e Maven no WSL:

```bash
sudo apt install openjdk-17-jdk maven
```

3. Execute a aplicação pelo terminal WSL integrado do IntelliJ
4. O PostgreSQL no WSL usa socket Unix, não TCP. Se houver problemas de conexão, verifique que o serviço está rodando:

```bash
sudo service postgresql status
sudo service postgresql start
```

5. Para permitir autenticação por senha, edite `/etc/postgresql/*/main/pg_hba.conf` e garanta que as linhas contêm `md5`:

```
local   all             postgres                                md5
local   all             all                                     md5
```

Reinicie o PostgreSQL após editar:

```bash
sudo service postgresql restart
```

## Troubleshooting

**Erro de autenticação PostgreSQL:**

Resete a senha do usuário postgres:

```bash
sudo -u postgres psql
ALTER USER postgres WITH PASSWORD 'postgres';
\q
```

**Porta 8080 já em uso:**

Mate o processo ou altere a porta em `application.properties`:

```properties
server.port=8081
```

**Erro de compilação Maven com Java 21:**

O projeto requer Java 17. Verifique a versão:

```bash
java -version
```

Se necessário, instale o Java 17 e configure como padrão.

## Licença

Apache License 2.0

## Autor

Caihan Garcia - [GitHub](https://github.com/caihan90)
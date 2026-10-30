# Auto Escola API

API REST desenvolvida em Java 17 e Spring Boot para o gerenciamento de instrutores e alunos de uma autoescola. O projeto contempla cadastro, consulta, listagem paginada, atualização cadastral, exclusão, validações e versionamento do banco de dados.

## Integrantes

- Diego Garcia Tosta — RM 556724
- Joud Jihad Jaber — RM 556482
- Lucca Pereira Locambo De Oliveira — RM 560731

> Confira se estes são os integrantes do grupo do Challenge antes de publicar o repositório.

## Tecnologias

- Java 17
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA / Hibernate
- Bean Validation
- PostgreSQL 17
- Flyway
- Maven

## Funcionalidades implementadas

### Instrutores

- Cadastro com nome, e-mail, telefone, CNH, especialidade e endereço completo.
- Listagem por nome em ordem crescente, com 10 registros por página.
- Consulta por identificador.
- Atualização somente de nome, telefone e endereço.
- Exclusão lógica: o registro permanece no banco e passa a ficar inativo.
- E-mail, CNH e especialidade são preservados após o cadastro.
- O telefone não é retornado nos objetos de resposta; ele é solicitado no cadastro e pode ser atualizado posteriormente.

### Alunos

- Cadastro com nome, e-mail, telefone, CPF e endereço completo.
- Validação de formato e dos dígitos verificadores do CPF.
- Listagem por nome em ordem crescente, com 10 registros por página.
- Consulta por identificador.
- Atualização somente de nome, telefone e endereço.
- E-mail e CPF são preservados após o cadastro.
- A exclusão de aluno foi implementada como exclusão física, pois o enunciado não especifica inativação para essa funcionalidade.

### Endereço

O endereço é representado por `EnderecoVO` e contém logradouro, número, complemento, bairro, cidade, UF e CEP. Número e complemento são opcionais; os demais campos são obrigatórios.

## Como executar

### 1. Pré-requisitos

- JDK 17 ou superior.
- Docker Desktop com Docker Compose.
- Conexão com a internet na primeira compilação para baixar as dependências Maven.

### 2. Iniciar o banco de dados

Na pasta raiz do projeto, execute:

```bash
docker compose up -d
```

O PostgreSQL ficará disponível em `localhost:5432`, com as seguintes configurações para desenvolvimento:

| Configuração | Valor |
|---|---|
| Banco | `autoescola` |
| Usuário | `autoescola` |
| Senha | `autoescola123` |
| Porta | `5432` |

### 3. Executar a API

No Windows:

```bat
mvnw.cmd spring-boot:run
```

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

O Flyway executa automaticamente as migrations que estiverem em `src/main/resources/db/migration`. O Hibernate está configurado com `ddl-auto=validate`, portanto não cria nem altera tabelas por conta própria.

### 4. Executar os testes

```bash
./mvnw test
```

No Windows, use `mvnw.cmd test`.

## Endpoints

Todas as rotas usam o prefixo `/api`.

| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/instrutores` | Cadastrar instrutor |
| GET | `/api/instrutores?page=0` | Listar instrutores ativos, 10 por página |
| GET | `/api/instrutores/{id}` | Consultar instrutor |
| PUT | `/api/instrutores/{id}` | Atualizar nome, telefone e endereço |
| DELETE | `/api/instrutores/{id}` | Inativar instrutor |
| POST | `/api/alunos` | Cadastrar aluno |
| GET | `/api/alunos?page=0` | Listar alunos, 10 por página |
| GET | `/api/alunos/{id}` | Consultar aluno |
| PUT | `/api/alunos/{id}` | Atualizar nome, telefone e endereço |
| DELETE | `/api/alunos/{id}` | Excluir aluno |

A paginação começa em zero. O tamanho é fixo em 10 registros, conforme o enunciado. As respostas de listagem incluem `content`, `page`, `size`, `totalElements`, `totalPages`, `first` e `last`.

## Exemplos de requisição

### Cadastrar instrutor

`POST http://localhost:8080/api/instrutores`

```json
{
  "nome": "Carlos Oliveira",
  "email": "carlos@autoescola.com.br",
  "telefone": "11987654321",
  "cnh": "12345678901",
  "especialidade": "CARROS",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": 120,
    "complemento": "Sala 2",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01001000"
  }
}
```

Valores aceitos para `especialidade`: `MOTOS`, `CARROS`, `VANS` e `CAMINHOES`.

### Cadastrar aluno

`POST http://localhost:8080/api/alunos`

```json
{
  "nome": "Mariana Santos",
  "email": "mariana@email.com",
  "telefone": "11999998888",
  "cpf": "52998224725",
  "endereco": {
    "logradouro": "Avenida Paulista",
    "numero": 1000,
    "complemento": "Apto 45",
    "bairro": "Bela Vista",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01310100"
  }
}
```

O CPF do exemplo é um número de demonstração válido para passar pela validação dos dígitos verificadores; não o utilize como dado real de cadastro.

### Atualizar instrutor

`PUT http://localhost:8080/api/instrutores/1`

```json
{
  "nome": "Carlos Oliveira Atualizado",
  "telefone": "11988887777",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": 120,
    "complemento": "Sala 3",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01001000"
  }
}
```

O DTO de atualização não possui os campos e-mail, CNH ou especialidade; portanto, eles não são alterados por essa operação. Para alunos, o DTO de atualização também não possui e-mail nem CPF.

### Listar a segunda página

```http
GET http://localhost:8080/api/instrutores?page=1
```

## Exemplos rápidos no IntelliJ / VS Code

O arquivo `docs/requisicoes.http` contém exemplos prontos para enviar as principais requisições usando um cliente HTTP compatível com o formato `.http`.

## Estrutura do projeto

```text
src/main/java/br/com/autoescola
├── controller       # Endpoints REST
├── dto              # Objetos de entrada, saída e paginação
├── entity           # Entidades JPA: Instrutor e Aluno
├── enums            # Especialidade do instrutor
├── exception        # Exceções e tratamento global de erros
├── repository       # Repositórios Spring Data JPA
├── service          # Regras de negócio
├── util             # Utilitários e conversores
└── vo               # Endereço como Value Object

src/main/resources/db/migration
└── V1__criar_tabelas_iniciais.sql
```

## Observações

- E-mails são normalizados para letras minúsculas antes de serem gravados.
- E-mail e CNH de instrutores são únicos; e-mail e CPF de alunos também são únicos.
- A API retorna `400` para dados inválidos, `404` para registros inexistentes e `409` para conflitos de dados únicos.
- Não foi implementado login/autorização, pois isso não aparece como requisito nesta entrega parcial.
- As credenciais do banco no `docker-compose.yml` são apenas para desenvolvimento local. Para implantação real, configure credenciais seguras por variáveis de ambiente.

## Entrega

O repositório deve ser publicado como público no GitHub. Depois de publicar, envie o link do repositório na atividade do Teams. O enunciado informa prazo de entrega em **02/11/2026** e que apenas um integrante do grupo precisa realizar o envio.

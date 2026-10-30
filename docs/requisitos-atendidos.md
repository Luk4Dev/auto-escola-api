# Rastreabilidade dos requisitos do Checkpoint 3

Este documento relaciona os itens dos enunciados com os arquivos e rotas implementados no projeto.

## Instrutores

| Requisito | Implementação |
|---|---|
| Cadastrar nome, e-mail, telefone, CNH, especialidade e endereço | `InstrutorCreateDTO`, `InstrutorService.cadastrar()` e `POST /api/instrutores` |
| Campos obrigatórios, exceto número e complemento do endereço | Anotações de validação em `InstrutorCreateDTO` e `EnderecoDTO` |
| Não exibir telefone inicialmente | O telefone é salvo, mas não consta em `InstrutorResponseDTO` nem em `InstrutorListagemDTO` |
| Listar nome, e-mail, CNH e especialidade | `InstrutorListagemDTO` e `GET /api/instrutores` |
| Ordenar pelo nome em ordem crescente | `Sort.Direction.ASC` no `InstrutorService` |
| Paginar 10 registros por página | Constante `TAMANHO_PAGINA = 10` no `InstrutorService` |
| Atualizar nome, telefone e endereço | `InstrutorUpdateDTO` e `PUT /api/instrutores/{id}` |
| Preservar e-mail, CNH e especialidade na atualização | Esses campos não existem no DTO de atualização e não são alterados pelo serviço |
| Excluir sem apagar os dados | `Instrutor.inativar()` e `DELETE /api/instrutores/{id}`; listagem e consulta retornam apenas instrutores ativos |

## Alunos

| Requisito | Implementação |
|---|---|
| Cadastrar nome, e-mail, telefone, CPF e endereço | `AlunoCreateDTO`, `AlunoService.cadastrar()` e `POST /api/alunos` |
| Campos obrigatórios, exceto número e complemento do endereço | Anotações de validação em `AlunoCreateDTO` e `EnderecoDTO` |
| Listar nome, e-mail e CPF | `AlunoListagemDTO` e `GET /api/alunos` |
| Ordenar pelo nome em ordem crescente | `Sort.Direction.ASC` no `AlunoService` |
| Paginar 10 registros por página | Constante `TAMANHO_PAGINA = 10` no `AlunoService` |
| Atualizar nome, telefone e endereço | `AlunoUpdateDTO` e `PUT /api/alunos/{id}` |
| Preservar e-mail e CPF na atualização | Esses campos não existem no DTO de atualização e não são alterados pelo serviço |
| Excluir aluno | `DELETE /api/alunos/{id}` |

## Requisitos técnicos do checkpoint

| Requisito | Implementação |
|---|---|
| Java 17 ou superior | `java.version` igual a `17` no `pom.xml` |
| API REST com Spring Boot | Controllers REST e aplicação `AutoEscolaApiApplication` |
| DTOs, VOs e entidades | Pacotes `dto`, `vo` e `entity` |
| Conexão com banco de dados | PostgreSQL configurado em `application.properties` |
| Validações | Jakarta Bean Validation e regra de validação dos dígitos do CPF |
| Controle de migrations | Flyway e migration `V1__criar_tabelas_iniciais.sql` |

# School Service API

Este é um serviço RESTful para gerenciar alunos, disciplinas e notas em uma instituição de ensino. O projeto foi desenvolvido utilizando Spring Boot e está hospedado no Railway.

*Projeto realizado como avaliação da disciplina Desenvolvimento de Serviços com Spring Boot, da Graduação em Engenharia de Computação - Instituto INFNET.*

## Endereço da Aplicação

A aplicação está hospedada no seguinte endereço:

[https://atschoolservice-production.up.railway.app/](https://atschoolservice-production.up.railway.app/)

## Autenticação
Este serviço utiliza autenticação básica (Basic Auth) para proteger os endpoints. Para acessar os recursos, você deve fornecer um nome de usuário e uma senha válidos.

### Usuário Padrão
Para facilitar os testes, um usuário foi configurado no sistema:


Usuário padrão:
 ```
Username: professor
Password: password
Role: USER
 ```

## Endpoints Disponíveis

### Alunos

- **GET /api/alunos**
  - Retorna a lista de todos os alunos.

- **POST /api/alunos**
  - Cria um novo aluno.
  - Exemplo de corpo da requisição:
    ```json
    {
      "nome": "João da Silva",
      "cpf": "123.456.789-00",
      "email": "joao.silva@example.com",
      "telefone": "123456789",
      "endereco": "Rua A, 123"
    }
    ```

### Disciplinas

- **GET /api/disciplinas**
  - Retorna a lista de todas as disciplinas.
  
- **POST /api/disciplinas**
  - Cria uma nova disciplina.
  - Exemplo de corpo da requisição:
    ```json
    {
      "nome": "Matemática",
      "codigo": "MAT101"
    }
    ```

### Notas

- **POST /api/notas**
  - Cria uma nova nota.
  - Exemplo de corpo da requisição:
    ```json
    {
      "aluno": {
        "id": 1
      },
      "disciplina": {
        "id": 1
      },
      "valor": 8.5
    }
    ```

- **GET /api/notas/aprovados**
  - Retorna a lista de alunos aprovados em uma disciplina específica.
  - Parâmetro: `disciplinaId`

- **GET /api/notas/reprovados**
  - Retorna a lista de alunos reprovados em uma disciplina específica.
  - Parâmetro: `disciplinaId`

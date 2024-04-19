# db_crud

Um simples crud de alunos, escolas e cursos.

## Alunos

### GET

Retorna Aluno por id
http://localhost:8080/alunos/2

### GET

Retorna Alunos matriculados em determinada Escola
http://localhost:8080/alunos/escola/2

### POST

Insere um Aluno
http://localhost:8080/alunos
This is a POST request, submitting data to an API via the request body. This request submits JSON data, and the data is reflected in the response.

A successful POST request typically returns a 200 OK or 201 Created response code.

#### Body

raw (json)
json
{
"nome": "José Alexandre",
"cpf": "2345475asdasdasd",
"curso": "Engenharia Mecatrônica",
"escola": {
"id": 2
}
}

### PUT

Atualiza Aluno por id
http://localhost:8080/alunos/2
This is a PUT request and it is used to overwrite an existing piece of data. For instance, after you create an entity with a POST request, you may want to modify that later. You can do that using a PUT request. You typically identify the entity being updated by including an identifier in the URL (eg. id=1).

A successful PUT request typically returns a 200 OK, 201 Created, or 204 No Content response code.

#### Body

raw (json)
json
{
"curso": "Engenharia Satânica",
"escola": {
"id": 2
}
}

### DELETE

Deleta Aluno por id
http://localhost:8080/alunos/2
This is a DELETE request, and it is used to delete data that was previously created via a POST request. You typically identify the entity being updated by including an identifier in the URL (eg. id=1).

A successful DELETE request typically returns a 200 OK, 202 Accepted, or 204 No Content response code.

## Escolas

### GET

Retorna Escola por id
http://localhost:8080/escolas/1

### POST

Insere uma Escola
http://localhost:8080/escolas

Request Headers
Content-Type
application/json

#### Body

raw (json)
json
{
"nome": "Veja a Vida",
"localizacao": "Rua Medeiros Neto 160A"
}

### POST

Atribui Curso em Escola por id
http://localhost:8080/escolas/assign/1/to/1

### PUT
Atualiza Escola por id
http://localhost:8080/escolas/1

Request Headers
Content-Type
application/json

#### Body

raw (json)
json
{
"nome": "aslkudhaksufbjn",
"localizacao": "asdasdasdads"
}

### DELETE

Deleta Escola por id
http://localhost:8080/escolas/1

## Cursos

### GET

Retorna Curso por id
http://localhost:8080/cursos/2

### GET

Retorna Cursos disponíveis em Escola por id
http://localhost:8080/cursos/escola/1

### POST

Insere um Curso
http://localhost:8080/cursos

Request Headers
Content-Type
application/json

#### Body

raw (json)
View More
json
{
"nome": "Engenharia da Computação",
"conteudo": "Administração, Automação Industrial"
}

### PUT

Atualiza Curso por id
http://localhost:8080/cursos/2

Request Headers
Content-Type
application/json

#### Body

raw (json)
View More
json
{
"id": 2,
"nome": "Engenharia Mecatrônica",
"conteudo": "Administração, Automação Industrial, Controle Estatístico"
}

### DELETE

Deleta Curso por id
http://localhost:8080/cursos/3
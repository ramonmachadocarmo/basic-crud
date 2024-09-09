# Projeto CRUD Básico com Spring + MySQL 8.0

## Stacks

- Java
- Spring
- JWT
- MySQL
- Docker
- Docker-compose

### Configuração

Para alteração de variáveis de ambiente, edite o arquivo .env

### Como executar?

Rodar projeto com docker-compose

```bash
docker-compose up -d
```

Para debugar, altere o HOST do conector JDBC:

```bash
spring.datasource.url=jdbc:mysql://mysql:3306/${MYSQL_DATABASE}
```

Digite o IP ou DNS do server MYSQL

```bash
spring.datasource.url=jdbc:mysql://IP_DO_SERVER:3306/${MYSQL_DATABASE}
```

### Testes unitários

Para rodar os testes unitários:

```bash
mvn test
```

### Autenticação

Para autenticar, executar post no path:

- Criar login

> /auth/signup

- Autenticar

> /auth/login

```json
{
  "login": "nome_usuário",
  "password": "senha"
}
```

License: [CC-BY](https://creativecommons.org/licenses/by/4.0/deed.en)

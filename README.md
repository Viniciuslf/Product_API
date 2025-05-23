
---

## 🧰 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Spring Data MongoDB**
- **JWT (JSON Web Token)**
- **Maven**
- **MongoDB**

---

## 🚀 Funcionalidades

- Cadastro e autenticação de usuários com encriptação de senha.
- Controle de acesso baseado em roles (`admin`, `user`).
- CRUD completo de Produtos e Categorias.
- Validações robustas de campos e mensagens de erro padronizadas.
- Filtros de busca em produtos (por nome, categoria, preço, disponibilidade etc).
- Timestamps automáticos (`createdAt`, `updatedAt`).

---

## 📦 Executando o Projeto

### Pré-requisitos

- Java 17+
- Maven
- MongoDB
- Docker

## Instale as dependências

Para instalar as dependências, execute:

```bash
mvn clean install
```

## Subir o container do banco de dados

Para subir o container do MongoDB, execute:

```bash
docker-compose up
```

## Rodar os testes unitários

Para rodar os testes, execute:

```bash
mvn test
```
## Diagrams
![image](https://github.com/user-attachments/assets/cec622d2-0811-4a84-82d1-ab8e5c3bd00a)

## Modelagem de ameaças
![security](https://github.com/user-attachments/assets/d0beec68-fe41-419c-8ae2-0ae000fb94de)



# 📢 API de Gerenciamento de Alertas (Quarkus + Hibernate)

Este projeto é uma API RESTful desenvolvida com **Quarkus**, utilizando **Hibernate ORM** para persistência em banco de dados relacional. A API permite o registro, consulta, atualização e exclusão de alertas relacionados a ocorrências em diferentes localidades, informadas por usuários.

## 🧩 Tecnologias Utilizadas

- [Quarkus](https://quarkus.io/)
- Java 17+
- JAX-RS (Jakarta REST)
- Hibernate ORM (JPA)
- Oracle Database (ou outro RDBMS compatível)
- Maven

## 📂 Estrutura do Projeto
src/
├── main/
│ ├── java/org/acme/
│ │ ├── controller/ # Camada REST (endpoints)
│ │ ├── bo/ # Camada de lógica de negócio (BO)
│ │ ├── service/ # Camada de serviço (regra de persistência)
│ │ ├── repository/ # Acesso ao banco de dados (JPA)
│ │ ├── entity/ # Entidades JPA
│ │ └── exception/ # Manipulação de exceções personalizadas
│ └── resources/
│ └── application.properties # Configurações do Quarkus


## 📌 Funcionalidades

- ✅ Listar todos os alertas cadastrados
- 🔍 Buscar alertas por e-mail do usuário
- 🆕 Cadastrar novo alerta com validações de campos obrigatórios
- ✏️ Atualizar dados de alerta com base no e-mail
- ❌ Excluir alerta por ID
- ⚠️ Validação de duplicidade de e-mails
- ⛔ Tratamento de exceções com retorno em formato JSON

## 📬 Endpoints da API

| Método | Rota                        | Descrição                              |
|--------|-----------------------------|----------------------------------------|
| GET    | `/reports`                  | Lista todos os alertas                 |
| GET    | `/reports/email/{email}`    | Retorna alertas por e-mail             |
| POST   | `/reports`                  | Cadastra um novo alerta                |
| PUT    | `/reports/email/{email}`    | Atualiza alertas vinculados ao e-mail  |
| DELETE | `/reports/id/{id}`          | Exclui um alerta pelo ID               |

## 📄 Exemplo de Payload (POST)

```json
{
  "email": "usuario@example.com",
  "name": "Nome do Usuário",
  "cidade": "São Paulo",
  "latitude": -23.5505,
  "longitude": -46.6333,
  "nivelGravidade": "moderada"
}
`````````
🚀 Como Executar o Projeto
Pré-requisitos

    Java 17+

    Maven 3.8+

    Oracle Database (ou outro banco compatível)

    Docker (opcional)

Passos:

    Clone o repositório:
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio

  Atualize as configurações do application.properties:
quarkus.datasource.jdbc.url=jdbc:oracle:thin:@//localhost:1521/SEUBANCO
quarkus.datasource.username=seu_usuario
quarkus.datasource.password=sua_senha
quarkus.hibernate-orm.database.generation=update
quarkus.datasource.db-kind=oracle

  Execute a aplicação:
./mvnw quarkus:dev


🧑‍💻 Autores
Lucas Fortes de Lima / Ana Eliza Kurt


📄 Licença
Este projeto está licenciado sob os termos da MIT License.

Projeto desenvolvido com fins acadêmicos para fins de aprendizado e experimentação com a stack Quarkus + JPA + REST.


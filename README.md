<div align="center">

# 📚 TechDocs — Central de Conhecimento Técnico

**Uma biblioteca centralizada para documentar, pesquisar e compartilhar procedimentos técnicos de TI.**

[!\[Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk\&logoColor=white)](https://www.oracle.com/java/)
[!\[Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?logo=springboot\&logoColor=white)](https://spring.io/projects/spring-boot)
[!\[PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?logo=postgresql\&logoColor=white)](https://www.postgresql.org/)
[!\[Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker\&logoColor=white)](https://www.docker.com/)
[!\[License](https://img.shields.io/badge/License-a%20definir-lightgrey)](#-licença)

*English description: A technical knowledge base for documenting, organizing, and searching IT procedures.*

</div>

\---

## 🎯 Sobre o projeto

O **TechDocs** é uma aplicação web para centralizar o conhecimento técnico de uma equipe de TI. Em vez de deixar instruções espalhadas em arquivos, mensagens e anotações, a equipe pode registrar procedimentos em uma biblioteca pesquisável.

O sistema foi desenvolvido como projeto de portfólio com foco em **desenvolvimento backend Java**, APIs REST, persistência de dados, testes automatizados e execução com Docker.

## 💡 Problema que resolve

Procedimentos de suporte, infraestrutura, banco de dados e sistemas podem ficar difíceis de localizar ou acabar desatualizados. O TechDocs organiza essas informações em um só lugar, facilitando a consulta e a manutenção da documentação.

## ✨ Funcionalidades

* 📊 **Dashboard:** visão geral dos documentos cadastrados, publicados, em rascunho e arquivados.
* 📝 **Gestão de documentos:** criar, consultar, editar e excluir registros.
* 🔎 **Pesquisa textual:** buscar por título, resumo, conteúdo, categoria ou responsável.
* 🗂️ **Filtros:** refinar a consulta por categoria e status.
* 🧭 **Categorias dinâmicas:** listar as categorias presentes na base.
* 📤 **Exportação JSON:** exportar metadados dos documentos.
* 📱 **Interface responsiva:** consultar a biblioteca em diferentes tamanhos de tela, com visualização em cartões ou lista.
* 🧪 **Testes automatizados:** testes de integração da API com JUnit, MockMvc e H2.
* ⚙️ **CI com GitHub Actions:** execução automatizada da suíte de testes em push para `main` e em pull requests.
* 🐳 **Ambiente Docker Compose:** aplicação e PostgreSQL executados como serviços separados.

## 🧰 Tecnologias utilizadas

|Área|Tecnologias|
|-|-|
|Linguagem|Java 21|
|Backend|Spring Boot 3.5, Spring Web|
|API|REST, Bean Validation|
|Persistência|Spring Data JPA, Hibernate|
|Banco de dados|PostgreSQL 16|
|Testes|JUnit 5, Spring Boot Test, MockMvc, H2|
|Frontend|HTML5, CSS3 e JavaScript|
|Build|Maven|
|Contêineres|Docker e Docker Compose|
|Automação|GitHub Actions|

## 🏗️ Arquitetura

A aplicação usa uma estrutura em camadas: a interface web faz requisições HTTP para a API REST; o controller encaminha as operações ao serviço; o serviço aplica as regras da aplicação e acessa o repositório, que persiste os dados no PostgreSQL por meio do JPA/Hibernate.

```text
Navegador
   │
   ▼
Frontend (HTML, CSS, JavaScript)
   │ HTTP / JSON
   ▼
DocumentController
   │
   ▼
DocumentService
   │
   ▼
DocumentRepository (Spring Data JPA)
   │
   ▼
PostgreSQL
```

O Spring Boot também serve os arquivos do frontend a partir de `src/main/resources/static`.

## 📂 Estrutura do repositório

```text
techdocs-knowledge-center/
├── .github/
│   └── workflows/
│       └── ci.yml                 # Pipeline de testes no GitHub Actions
├── src/
│   ├── main/
│   │   ├── java/com/techdocs/
│   │   │   ├── common/
│   │   │   │   └── ApiExceptionHandler.java
│   │   │   ├── document/
│   │   │   │   ├── DemoDataInitializer.java
│   │   │   │   ├── DocumentController.java
│   │   │   │   ├── DocumentNotFoundException.java
│   │   │   │   ├── DocumentRepository.java
│   │   │   │   ├── DocumentService.java
│   │   │   │   ├── DocumentStatus.java
│   │   │   │   └── TechnicalDocument.java
│   │   │   └── TechDocsApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── app.js
│   │       │   ├── index.html
│   │       │   └── styles.css
│   │       └── application.properties
│   └── test/
│       └── java/com/techdocs/document/
│           └── DocumentControllerTest.java
├── .dockerignore
├── .env.example
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
```

## 🚀 Como executar com Docker

### Pré-requisitos

* Docker Engine ou Docker Desktop;
* Docker Compose v2.

### 1\. Baixe o projeto

```bash
git clone https://github.com/devbymarks/techdocs-knowledge-center.git
cd techdocs-knowledge-center
```

> Se o repositório tiver outro nome ou ainda não estiver publicado, entre na pasta em que você extraiu o projeto.

### 2\. Configure as variáveis de ambiente

Linux/macOS:

```bash
cp .env.example .env
```

No Windows PowerShell:

```powershell
Copy-Item .env.example .env
```

Edite o arquivo `.env` e defina uma senha própria para `POSTGRES\_PASSWORD`. O valor de exemplo é apenas para desenvolvimento local e não deve ser usado em produção.

### 3\. Inicie os serviços

```bash
docker compose up --build -d
docker compose ps
```

Acesse a aplicação em **http://localhost:8080**.

### 4\. Consulte os logs

```bash
docker compose logs -f app
```

Para acompanhar o banco:

```bash
docker compose logs -f db
```

### 5\. Encerre o ambiente

```bash
docker compose down
```

Os dados ficam no volume Docker `techdocs\_data`. Para apagar também o banco persistido e começar do zero:

```bash
docker compose down -v
```

**Atenção:** `docker compose down -v` remove os dados armazenados no volume do banco.

## ☕ Executar localmente sem Docker

### Pré-requisitos

* JDK 21;
* Maven 3.9 ou superior;
* PostgreSQL 16.

Crie um banco chamado `techdocs` e um usuário com permissão de acesso. Configure as variáveis de ambiente antes de iniciar a aplicação.

Linux/macOS:

```bash
export SPRING\_DATASOURCE\_URL='jdbc:postgresql://localhost:5432/techdocs'
export SPRING\_DATASOURCE\_USERNAME='techdocs'
export SPRING\_DATASOURCE\_PASSWORD='sua-senha-local'

mvn spring-boot:run
```

Windows PowerShell:

```powershell
$env:SPRING\_DATASOURCE\_URL = "jdbc:postgresql://localhost:5432/techdocs"
$env:SPRING\_DATASOURCE\_USERNAME = "techdocs"
$env:SPRING\_DATASOURCE\_PASSWORD = "sua-senha-local"

mvn spring-boot:run
```

Depois, abra http://localhost:8080.

## 🧪 Executar os testes

Com JDK 21 e Maven instalados:

```bash
mvn test
```

Para executar a verificação completa usada no CI:

```bash
mvn verify
```

Os testes configuram o banco H2 em memória; não é necessário iniciar o PostgreSQL para executá-los.

## 🔌 API REST

Base URL: `http://localhost:8080/api/documents`

|Método|Endpoint|Descrição|
|-|-|-|
|`GET`|`/api/documents`|Lista os documentos|
|`GET`|`/api/documents?q=backup`|Pesquisa por texto|
|`GET`|`/api/documents?category=Linux`|Filtra por categoria|
|`GET`|`/api/documents?status=PUBLISHED`|Filtra por status|
|`GET`|`/api/documents/{id}`|Consulta um documento pelo ID|
|`POST`|`/api/documents`|Cadastra um documento|
|`PUT`|`/api/documents/{id}`|Atualiza um documento|
|`DELETE`|`/api/documents/{id}`|Exclui um documento|
|`GET`|`/api/documents/stats`|Retorna indicadores do dashboard|
|`GET`|`/api/documents/categories`|Lista as categorias existentes|
|`GET`|`/api/documents/export`|Exporta metadados em JSON|

### Exemplo: cadastrar um documento

```bash
curl -X POST http://localhost:8080/api/documents \\
  -H 'Content-Type: application/json' \\
  -d '{
    "title": "Validar backup PostgreSQL",
    "category": "Backup",
    "author": "Equipe de TI",
    "summary": "Verificar geração e integridade do arquivo.",
    "content": "1. Confira o log. 2. Valide o tamanho. 3. Teste uma restauração em ambiente controlado.",
    "status": "DRAFT"
  }'
```

Status disponíveis: `DRAFT` (rascunho), `PUBLISHED` (publicado) e `ARCHIVED` (arquivado).

## 🔐 Segurança e limitações conhecidas

* O projeto não implementa autenticação, autorização por perfil ou trilha de auditoria.
* Não exponha a aplicação publicamente nem armazene senhas, tokens, chaves privadas, dados pessoais ou procedimentos confidenciais.
* A configuração `spring.jpa.hibernate.ddl-auto=update` facilita o desenvolvimento; em produção, prefira migrações versionadas e uma estratégia controlada de evolução do banco.
* Configure uma senha forte e exclusiva fora do ambiente local.
* A exportação atual disponibiliza metadados em JSON; exportação CSV e anexos não estão implementados.
* A aplicação ainda não possui paginação na listagem.

## 🗺️ Roadmap

* \[ ] Implementar autenticação e autorização por função.
* \[ ] Criar histórico de alterações e fluxo de revisão de documentos.
* \[ ] Adicionar exportação CSV e suporte a anexos.
* \[ ] Adicionar migrações de banco com Flyway.
* \[ ] Implementar paginação e ordenação configurável.
* \[ ] Documentar a API com OpenAPI/Swagger.
* \[ ] Ampliar a cobertura de testes e validar fluxos de erro.

## 👨‍💻 Autor

**Matheus Marks**

Projeto desenvolvido com foco em centralização do conhecimento técnico, documentação de procedimentos e organização de informações operacionais de TI.

O TechDocs permite reunir orientações e procedimentos em uma biblioteca pesquisável, facilitando o compartilhamento de conhecimento, a padronização de rotinas e a consulta por equipes técnicas.



\---

## ⭐ Apoie o projeto

Se este projeto foi útil para você, considere deixar uma ⭐ no repositório. 






# Subfy
### Plataforma de Gerenciamento de Assinaturas Digitais

Projeto Integrador 2025

---

## Sobre o Projeto

O Subfy e uma plataforma SaaS de gerenciamento de assinaturas digitais. O sistema permite que usuarios controlem seus servicos recorrentes como Netflix, Spotify, Amazon Prime, YouTube Premium, entre outros, acompanhando valores, datas de cobranca e recebendo alertas automaticos de vencimento.

---

## Funcionalidades

- Cadastro e login com autenticacao JWT
- Dashboard com metricas financeiras em tempo real
- Criar, editar, cancelar e excluir assinaturas
- Alertas de vencimento no app (sino de notificacoes)
- Notificacoes automaticas por email as 08h
- Envio manual de alertas por email
- Dark mode e Light mode
- Sistema multiusuario (cada conta ve apenas seus dados)
- Pagina de planos com modal interativo

---

## Tecnologias Utilizadas

### Frontend
- React.js
- CSS Variables (Design System proprio)
- Lucide React (icones)
- React Router DOM

### Backend
- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA / Hibernate
- Spring Mail
- Spring Scheduler

### Banco de Dados
- PostgreSQL (porta 5433)

### Integracao Externa
- Gmail SMTP (notificacoes por email)

---

## Arquitetura

O projeto segue a arquitetura Cliente-Servidor em Camadas:

```
Frontend (React)  <-->  Backend (Spring Boot)  <-->  PostgreSQL
     :3000                    :8080                    :5433
```

Dentro do backend, arquitetura em 3 camadas:

```
Controller  ->  Service  ->  Repository  ->  Banco de Dados
```

---

## Padroes de Projeto Aplicados

- Singleton — beans gerenciados pelo Spring
- Repository Pattern — abstração do banco via JPA
- Facade — NotificationController esconde complexidade do Scheduler
- MVC — separacao entre Model, View e Controller
- REST — API stateless com verbos HTTP semanticos
- JWT — autenticacao stateless com token

---

## Como Rodar o Projeto

### Pre-requisitos

- Java 17+
- Maven
- Node.js 18+
- PostgreSQL

### Banco de Dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE subfy;
```

### Backend

```bash
cd subfy
```

Copie o arquivo de configuracao:

```bash
copy application.properties.example src\main\resources\application.properties
```

Preencha suas credenciais no `application.properties` e rode:

```bash
mvn spring-boot:run
```

Backend disponivel em: http://localhost:8080

### Frontend

```bash
cd subfy-front
npm install
npm start
```

Frontend disponivel em: http://localhost:3000

### Ordem de inicializacao

1. PostgreSQL (banco de dados)
2. Backend (mvn spring-boot:run)
3. Frontend (npm start)

---

## Endpoints da API

| Metodo | Endpoint | Auth | Descricao |
|--------|----------|------|-----------|
| POST | /auth/register | Publica | Cadastra novo usuario |
| POST | /auth/login | Publica | Autentica e retorna token JWT |
| GET | /subscriptions/{email} | JWT | Lista assinaturas do usuario |
| POST | /subscriptions | JWT | Cria nova assinatura |
| PUT | /subscriptions/{id} | JWT | Edita ou cancela assinatura |
| DELETE | /subscriptions/{id} | JWT | Remove assinatura |
| POST | /notifications/send | JWT | Dispara alertas por email |

---

## Documentacao

O projeto possui documentacao completa de Design de Software incluindo:

- Diagrama C4 Nivel 1 (Contexto)
- Diagrama C4 Nivel 2 (Containers)
- Modelagem de Classes de Dominio (DDD)
- Padroes de Projeto aplicados
- Endpoints da API REST

---

## Estrutura do Projeto

```
Subfy/
├── subfy/                          # Backend Spring Boot
│   ├── src/main/java/com/subfy/
│   │   ├── controller/             # Endpoints REST
│   │   ├── entity/                 # Entidades JPA
│   │   ├── repository/             # Acesso ao banco
│   │   ├── security/               # JWT + Spring Security
│   │   └── service/                # Regras de negocio
│   ├── application.properties.example
│   └── pom.xml
│
└── subfy-front/                    # Frontend React
    └── src/
        ├── App.js
        ├── Dashboard.js
        ├── Login.js
        ├── Register.js
        ├── Subscriptions.js
        ├── Plans.js
        ├── Profile.js
        ├── Layout.js
        ├── NotificationSystem.js
        └── index.css
```

---

Desenvolvido como Projeto
Adison de Oliveira


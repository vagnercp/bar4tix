# 🧱 Bar4tix — Infraestrutura e Migrations

Este módulo contém toda a **infraestrutura de banco de dados** e o controle de **migrations versionadas** com **Flyway**.

A arquitetura é multi-schema (um schema por serviço), mantendo histórico de migrações **isolado por schema**.

---

## 📂 Estrutura de Pastas

```
infra/
 ├─ docker-compose.yml        # Stack Postgres + Flyway
 ├─ Makefile                  # Atalhos para comandos
 ├─ Jenkinsfile               # Pipeline CI/CD de migrations
 ├─ flyway/
 │   ├─ flyway.conf           # Configuração base
 │   └─ env/
 │       ├─ dev.conf
 │       ├─ stg.conf
 │       └─ prd.conf
 └─ migrations/
     ├─ 00_infra/db/          # Bootstrap inicial (schemas, extensões)
     ├─ idp/db/               # Identity Provider (idp.*)
     ├─ prof/db/              # Profile (prof.*)
     ├─ catalog/db/           # Catálogo e produtos (catalog.*)
     ├─ places/db/            # Localização e estabelecimentos (places.*)
     ├─ events/db/            # Eventos e Outbox (evt.*)
     └─ vapp/db/              # Cache e integrações (vapp.*)
```

---

## 🚀 Subindo o ambiente

1️⃣  **Suba o PostgreSQL**

```
docker compose up -d postgres
```

2️⃣  **Rode as migrations**

```
docker compose --profile migrations run --rm flyway-infra \
 && docker compose --profile migrations run --rm flyway-catalog \
 && docker compose --profile migrations run --rm flyway-places \
 && docker compose --profile migrations run --rm flyway-events \
 && docker compose --profile migrations run --rm flyway-idp \
 && docker compose --profile migrations run --rm flyway-prof \
 && docker compose --profile migrations run --rm flyway-vapp
 
```
# ou via Makefile
```
make migrate
```


3️⃣  **Verifique o estado das migrations**

```
make info
```
---

## 🧩 Organização dos Schemas

Cada serviço possui seu próprio schema e histórico de versões independente.

| Serviço | Schema | Histórico | Exemplo de Migration |
|----------|---------|------------|----------------------|
| Identity | `idp` | `idp.flyway_schema_history` | `V1.0.0__init_idp.sql` |
| Profile  | `prof` | `prof.flyway_schema_history` | `V1.0.0__init_prof.sql` |
| Catalog  | `catalog` | `catalog.flyway_schema_history` | `V1.1.0__price_aliases.sql` |
| Places   | `places` | `places.flyway_schema_history` | `V1.2.0__checkins.sql` |
| Events   | `evt` | `evt.flyway_schema_history` | `V1.3.0__outbox.sql` |
| App Core | `vapp` | `vapp.flyway_schema_history` | `V1.0.0__init_vapp_cache.sql` |

---

## ⚙️ Convenção de Versões

O padrão segue o **SemVer-like** (major.minor.patch):

| Tipo | Exemplo | Descrição |
|------|----------|-----------|
| Major | `V2.0.0__rebuild_schema.sql` | Quebra de compatibilidade |
| Minor | `V1.1.0__add_table.sql` | Nova feature, compatível |
| Patch | `V1.0.1__fix_default.sql` | Correção ou ajuste |

> 💡 O Flyway executa apenas versões **posteriores** à última aplicada.  
> Se a última foi `V1.3.0`, a próxima deve ser `≥ 1.3.1` ou `1.4.0`.

---

## 🧰  Makefile (atalhos)

| Comando | Descrição |
|----------|------------|
| `make migrate` | Executa todas as migrations |
| `make info` | Lista estado das migrations |
| `make repair` | Repara histórico de migrações |
| `make clean` | Remove containers e volumes temporários |

---

## ☁️  Jenkins Pipeline

O `Jenkinsfile` já possui stages separados por ambiente:

```groovy
stage('Migrate DEV') {
    steps {
        sh 'make migrate ENV=dev'
    }
}
stage('Migrate STG') {
    steps {
        sh 'make migrate ENV=stg'
    }
}
stage('Migrate PRD') {
    steps {
        input "Aprovar deploy em Produção?"
        sh 'make migrate ENV=prd'
    }
}
```

---

## 🧩 Boas práticas

- Cada alteração de schema → **nova migration**.
- Nunca altere uma migration já aplicada.
- Teste localmente antes de subir ao repositório.
- Use scripts `R__*.sql` apenas para rotinas repetitivas ou helpers (não versionadas).

---

## 📜 Licença
MIT © Bar4tix Platform
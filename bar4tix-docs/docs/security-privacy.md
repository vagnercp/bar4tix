# 🔐 Segurança & Privacidade — Bar4tix (v0.1)

## 🧭 Propósito

Garantir que o ecossistema Bar4tix opere sob **princípios de segurança, transparência e conformidade**, protegendo dados pessoais e corporativos em todo o ciclo de vida: coleta, processamento, armazenamento e uso analítico.

> “Privacidade por design, segurança por padrão.”

---

## 📜 Fundamentos Legais (LGPD + PCI-DSS)

| Pilar                 | Princípio                                                       | Implementação                                            |
| --------------------- | --------------------------------------------------------------- | -------------------------------------------------------- |
| **Finalidade**        | Coleta apenas para fins legítimos (comparação, BI e relatórios) | Consentimentos granulares no app e portal                |
| **Necessidade**       | Apenas o mínimo necessário é tratado                            | Segregação de dados por schema (identity, coupon, price) |
| **Transparência**     | Usuários sabem como seus dados são usados                       | Política de privacidade pública + dashboard “Meus Dados” |
| **Segurança**         | Medidas técnicas e administrativas adequadas                    | Criptografia, auditoria, tokenização e RBAC              |
| **Prevenção**         | Mitigar riscos de uso indevido ou vazamento                     | Monitoramento e alertas de acesso suspeito               |
| **Responsabilização** | Governança de privacidade e DPO ativo                           | Relatórios trimestrais de compliance                     |
| **Consentimento**     | Acordo claro e revogável a qualquer momento                     | LGPD module: consent_flags + audit trail                 |

---

## 🧩 Pilares Técnicos

### 🔒 Consentimento & Portabilidade

* Consentimento granular (ex: dados de nota, geolocalização, cashback).
* Revogação instantânea via app (“Esquecer meus dados”).
* Portabilidade: export JSON/CSV via painel do usuário.
* Registro de consentimento versionado (`identity.consent_audit`).

### 🧠 Pseudonimização & Minimização

| Tipo de dado                    | Estratégia                                   | Local       |
| ------------------------------- | -------------------------------------------- | ----------- |
| CPF / CNPJ                      | Hash com sal (SHA-256 + salt)                | `identity`  |
| Localização                     | Arredondamento geográfico (4 casas decimais) | `places`    |
| Cupom NFC-e                     | Hash da chave + ID anônimo do usuário        | `coupon`    |
| Dados financeiros (PSP, wallet) | Tokenização                                  | `payment`   |
| Dados de analytics              | ID anônimo persistente (UUIDv4)              | `telemetry` |

> Nenhum dado pessoal é armazenado em logs ou mensagens Kafka.

### 🧱 Mínimo Necessário & Segregação

* **Schemas isolados por domínio:** `identity`, `coupon`, `price`, `market`, `payment`.
* **RBAC (Role-Based Access Control):** papéis e escopos definidos por contexto.
* **Feature toggles por tenant/região** para controle fino de acesso.
* **Ambientes PCI/LGPD isolados** em namespaces dedicados.

### 🔐 Criptografia

| Tipo                     | Implementação                                         |
| ------------------------ | ----------------------------------------------------- |
| **Em trânsito**          | TLS 1.3 obrigatório (Ingress Traefik e APIs internas) |
| **Em repouso (at rest)** | Volumes, snapshots e buckets criptografados (AES-256) |
| **Chaves e segredos**    | Vault + rotação automática                            |
| **Dados sensíveis**      | Tokenização para PCI e hashing para LGPD              |
| **Logs e backups**       | Compressão + criptografia assíncrona                  |

### 🧾 Auditoria & Rastreabilidade

* Todo acesso a dados pessoais é logado com `traceId`, `actorId` e `scope`.
* Logs estruturados (Loki) com política de retenção de 90 dias.
* Auditorias automáticas para detectar acesso fora do escopo.
* Módulo `audit.events` com trilha imutável (append-only).

### ⏳ Retenção & Expurgo

| Origem                 | Retenção          | Ação Pós-Prazo                |
| ---------------------- | ----------------- | ----------------------------- |
| Cupom NFC-e            | 12 meses          | Hash e expurgo de metadados   |
| Panfleto (OCR)         | 6 meses           | Exclusão do arquivo bruto     |
| Check-in / localização | 3 meses           | Arredondamento e anonimização |
| Métricas e logs        | 90 dias           | Rotação e compressão          |
| Dados PCI              | 0 (não armazenar) | Tokenização via PSP           |

---

## 👤 Acesso & Perfis

| Perfil                   | Acesso                                   | Escopo                            |
| ------------------------ | ---------------------------------------- | --------------------------------- |
| **App (Consumidor)**     | Dados próprios, histórico e cashback     | Via app com login e consentimento |
| **Lojista (B2B)**        | BI, preços regionais, concorrência       | Filiais do próprio CNPJ           |
| **Indústria (B2B)**      | Relatórios e API anonimizados            | Dados agregados e regionais       |
| **Gestor Público (B2G)** | Painéis “Cesta Bar4tix” e índices locais | Dados públicos e agregados        |
| **Admin (Suporte)**      | Diagnóstico técnico e auditoria          | Acesso monitorado e limitado      |
| **DPO/Compliance**       | Logs e consentimentos                    | Apenas leitura/auditoria          |

---

## 🧮 Controles Operacionais

* **CI/CD com scans de segurança (Snyk/OWASP).**
* **Secrets as Code:** variáveis nunca commitadas.
* **Backups diários criptografados.**
* **Monitoramento ativo de intrusão (IDS).**
* **Pentest semestral e auditoria LGPD anual.**

---

## ⚖️ Governança e Responsabilidade

* **DPO** designado (Data Protection Officer).
* **Política de Privacidade publicada e versionada.**
* **Treinamento obrigatório para devs e suporte.**
* **Avaliação de impacto (DPIA)** para novos módulos.
* **Integração direta com `audit-service` e `security-monitor`.**

---

## 🧭 Matriz de Conformidade — LGPD × Controles Técnicos

| Princípio LGPD        | Controle Técnico                     | Ferramenta/Implementação            |
| --------------------- | ------------------------------------ | ----------------------------------- |
| **Finalidade**        | Consentimento granular + escopos API | Spring Security / Consent Module    |
| **Necessidade**       | RBAC + segregação de schema          | PostgreSQL schemas + JWT claims     |
| **Transparência**     | Dashboard “Meus Dados”               | Front + Identity Service            |
| **Segurança**         | Criptografia at rest/in transit      | Vault / TLS / AES-256               |
| **Prevenção**         | IDS + monitoramento de logs          | Loki + Prometheus Alerts            |
| **Não discriminação** | Dados anonimizados                   | SpEL anonymizer / Hash salts        |
| **Responsabilização** | Auditoria de acesso + DPO ativo      | `audit.events` + Compliance Reports |

---

**Versão:** v0.1
**Autor:** Vagner Coelho Pinto
**Data:** 2025-10-14
**Licença:** CC-BY-NC-SA 4.0

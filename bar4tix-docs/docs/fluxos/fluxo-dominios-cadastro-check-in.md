# Fluxos — Cadastro Unificado & Check‑in

> Documento em Markdown com dois fluxos em **Mermaid**: (1) **Cadastro Unificado** (social + dados complementares) e (2) **Check‑in & Cadastro de Estabelecimento** com processamento assíncrono.

---

## 1) Cadastro Unificado — BigTechs + Dados Complementares

```mermaid
flowchart LR
  %% CLIENTE / FRONT
  subgraph Client[Cliente]
    A[Usuário escolhe ''Entrar com Provedor'']
    B[Redireciona para IdP Google, Apple...]
    P[Formulário de dados pessoais e endereço]
    Q[Envio dos dados complementares]
    R[Confirmação e dashboard inicial]
  end

  %% IDENTITY PROVIDER
  subgraph IdP[Provedor de Identidade]
    C[Tela de login/consentimento]
    D[Autorização concedida -> Authorization Code]
  end

  %% AUTH BACKEND
  subgraph Auth[Backend de Autenticação]
    E[Troca Code por Tokens OIDC/OAuth]
    F[Valida id_token iss, aud, exp, nonce]
    G[Upsert Usuário Local]
    H[Emitir JWT/Sessão]
    I[Registrar evento: auth.user_registered/login]
  end

  %% PROFILE API
  subgraph Profile[Profile API]
    J[Validar formato dos dados]
    K[Persistir dados pessoais e endereço]
    L[Emitir evento: profile.updated]
  end

  %% LIGAÇÕES
  A --> B --> C --> D --> E --> F --> G --> H --> I
  H --> P --> Q --> J --> K --> L --> R

  %% ERROS E PROTEÇÕES
  D -. nega/erro .-> X[Exibe erro e opção de tentar novamente]
  B --> Y[PKCE + state/nonce]
  E --> Z[Rate-limit e timeout IdP]

  %% OBSERVABILIDADE
  I --> M[Métricas: auth.success/fail]
  L --> N[Métricas: profile.updated]

  %% DADOS E EVENTOS
  subgraph Data[Eventos/Dados]
    O[auth.user_registered/profile.updated]
  end

  I --> O
  L --> O

  %% DESCRIÇÃO GERAL
  %% Este fluxo une o login via provedores BigTechs e o cadastro de dados complementares.
  %% Após autenticação (Google/Apple), o usuário fornece dados pessoais mínimos e endereço opcional.
  %% Ambos os passos disparam eventos para observabilidade e BI.
```

---

## 2) Check‑in do Usuário & Cadastro de Estabelecimento (assíncrono)

```mermaid
flowchart LR
  %% CLIENTE
  subgraph Client[Cliente]
    A[Usuário inicia Check-in]
    B[App coleta geolocalização]
    C[Enviar para BFF/API]
    R[Retorno imediato: Recebido/Pendente]
  end

  %% API / BFF
  subgraph API[Check-in API]
    D[Buscar estabelecimento por raio]
    E{Encontrou?}
    F[Criar rascunho de estabelecimento]
    G[Publicar evento place.resolve]
    H[Publicar evento checkin.process]
  end

  %% WORKERS
  subgraph Workers[Workers]
    J[Resolver estabelecimento enriquecer/normalizar]
    K[Atualizar base: criar/ligar estabelecimento]
    L[Processar check-in validar/consolidar]
    M[Emitir eventos finais]
  end

  %% LIGAÇÕES PRINCIPAIS
  A --> B --> C --> D --> E
  E -- Sim --> H --> R
  E -- Não --> F --> G --> H --> R

  %% ASSÍNCRONO
  G --> J --> K
  H --> L
  K --> M
  L --> M

  %% EVENTOS
  subgraph Events[Tópicos/Eventos]
    T1[place.resolve]
    T2[checkin.process]
    T3[place.created/linked]
    T4[checkin.recorded]
    T5[bi.event]
  end

  G --> T1
  H --> T2
  K --> T3
  L --> T4
  M --> T5

  %% REGRAS
  subgraph Rules[Regras]
    X[Idempotência por checkin_id]
    Y[Rate limit por usuário]
    Z[Pseudonimização para BI]
  end

  C --> X
  C --> Y
  M --> Z
```

---

### Eventos (resumo)

* `auth.user_registered`, `auth.user_login`
* `profile.updated`
* `place.resolve`, `place.created`, `place.linked`
* `checkin.process`, `checkin.recorded`
* `bi.event`

### Observações

* **LGPD/Privacidade**: pseudonimizar `user_id` para BI; cifrar PII em repouso.
* **Confiabilidade**: Outbox/Idempotência na publicação de eventos; retentativas exponenciais.
* **Métricas**: latência por etapa, taxa de sucesso/falha, % de dedupe de estabelecimento.

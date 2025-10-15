# Fluxo — Domínio Ingestion (Panfletos)

> **Objetivo:** coletar panfletos de estabelecimentos de forma **backend-driven** (sem interação do usuário), extrair itens e publicar eventos.

## 🔗 Navegação
- [⬅️ Voltar para README geral dos fluxos](./README.md)
- [📄 Fluxo QR-Code (Receipts)](./fluxo-dominios-qrcode.md)
- [📄 Fluxo Pricing](./fluxo-dominios-pricing.md)
- [📄 Fluxo Analytics](./fluxo-dominios-analytics.md)
- [📄 Fluxo Identity & Privacy](./fluxo-dominios-identity.md)

## 🧩 Diagrama

```mermaid
flowchart LR

%% ---------- Orquestração ----------
subgraph S ["Ingestion Orquestrador"]
  S1["Scheduler (cron)"]
  S2["Crawler Manager<br/>(rate-limit, robots, retries)"]
end

%% ---------- Fontes ----------
subgraph F ["Fontes de Dados"]
  F1["Site do Estabelecimento<br/>(HTML/PDF/Imagem)"]
  F2["API do Estabelecimento<br/>(quando houver)"]
end

%% ---------- Pipeline ----------
subgraph I ["Pipeline de Ingestion (backend)"]
  B1["Download/Fetch"]
  B2["OCR/Extracao de Itens"]
  B3["Normalizacao/Matching<br/>(produto, loja)"]
  B4["Validacao & Qualidade<br/>(regras, score)"]
  Q1["Fila: ingestion.out<br/>(eventos)"]
end

%% ---------- Downstream ----------
subgraph D3 ["Dominio Pricing"]
  C1["Consome eventos (ingestion)"]
  C2["Compara precos e atualiza"]
  C3["Publica PriceUpdatedEvent"]
end

subgraph D5 ["Dominio Analytics"]
  A1["Log de extracao/erros"]
  A2["Metricas agregadas"]
end

%% ---------- Erros ----------
subgraph E ["Tratamento de Falhas"]
  R1["Retry com backoff"]
  R2["DLQ ingestion.dlq"]
end

%% ---------- Fluxos ----------
S1 --> S2
S2 --> B1

F1 --> B1
F2 --> B1

B1 --> B2
B2 --> B3
B3 --> B4

B4 -->|OK| Q1
B4 -->|Falha| R1
R1 -->|esgotou| R2

Q1 --> C1
C1 --> C2
C2 --> C3

%% ---------- Observabilidade ----------
B1 -.-> A1
B2 -.-> A1
B3 -.-> A1
B4 -.-> A1
A1 --> A2

%% ---------- Estilos ----------
style S fill:#fff8dc,stroke:#8b6f00,stroke-width:1px
style F fill:#eef7ff,stroke:#3b7ddd,stroke-width:1px
style I fill:#f7fff0,stroke:#3b7d3b,stroke-width:1px
style E fill:#fff0f0,stroke:#cc6666,stroke-width:1px
```

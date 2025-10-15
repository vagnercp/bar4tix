# Fluxo — Domínio Analytics

> **Objetivo:** produzir métricas públicas agregadas e relatórios sem expor dados pessoais — visão visual.

## 🔗 Navegação
- [⬅️ Voltar para README geral dos fluxos](./README.md)
- [📄 Fluxo QR-Code (Receipts)](./fluxo-dominios-qrcode.md)
- [📄 Fluxo Panfleto (Ingestion)](./fluxo-dominios-panfleto.md)
- [📄 Fluxo Pricing](./fluxo-dominios-pricing.md)
- [📄 Fluxo Identity & Privacy](./fluxo-dominios-identity.md)

## 🧩 Diagrama

```mermaid
flowchart LR

subgraph S ["Fontes de Eventos"]
  S1["PriceUpdatedEvent"]
  S2["Logs de ingestion/receipts"]
end

subgraph ETL ["Processos Analytics"]
  T1["Ingestao (stream/batch)"]
  T2["Transformacao e agregacoes"]
  T3["Publicacao de datasets"]
end

subgraph PUB ["Saidas Publicas"]
  P1["Dashboards / Paineis"]
  P2["Exportacoes (CSV/Parquet)"]
end

subgraph PRIV ["Controles de Privacidade"]
  V1["Anonimizacao (user_hash)"]
  V2["Mascaramento (sem CPF)"]
end

S1 --> T1
S2 --> T1
T1 --> T2 --> T3
T3 --> P1
T3 --> P2
V1 -.-> T2
V2 -.-> T3

style PRIV fill:#fff8dc,stroke:#8b6f00,stroke-width:1px
style PUB fill:#eef7ff,stroke:#3b7ddd,stroke-width:1px
```

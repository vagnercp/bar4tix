# Fluxo — Domínio Identity & Privacy

> **Objetivo:** manter identidade e consentimentos separados, com vinculação reversível controlada e visão privada ao usuário.

## 🔗 Navegação
- [⬅️ Voltar para README geral dos fluxos](./README.md)
- [📄 Fluxo QR-Code (Receipts)](./fluxo-dominios-qrcode.md)
- [📄 Fluxo Panfleto (Ingestion)](./fluxo-dominios-panfleto.md)
- [📄 Fluxo Pricing](./fluxo-dominios-pricing.md)
- [📄 Fluxo Analytics](./fluxo-dominios-analytics.md)

## 🧩 Diagrama

```mermaid
flowchart LR

subgraph ID ["Identity & Privacy"]
  I1["Cadastro/Consentimento"]
  I2["Gerar user_hash (salted)"]
  I3["Tabela criptografada: user_identity"]
  I4["Mapeamento: user_hash ↔ CPF"]
end

subgraph EVT ["Eventos do Usuario"]
  E1["Ingestao de cupons"]
  E2["Atualizacao de itens"]
end

subgraph VIEW ["Visao Privada do Usuario"]
  V1["Meus totais (ultimos 10 dias)"]
end

I1 --> I2 --> I3 --> I4
E1 --> V1
E2 --> V1
I4 -.-> V1

style ID fill:#fff8dc,stroke:#8b6f00,stroke-width:1px
style VIEW fill:#eef7ff,stroke:#3b7ddd,stroke-width:1px
```

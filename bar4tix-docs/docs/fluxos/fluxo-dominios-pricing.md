# Fluxo — Domínio Pricing

> **Objetivo:** consolidar preços a partir de eventos (cupons e panfletos), atualizar leitura e disparar métricas — visão visual.

## 🔗 Navegação
- [⬅️ Voltar para README geral dos fluxos](./README.md)
- [📄 Fluxo QR-Code (Receipts)](./fluxo-dominios-qrcode.md)
- [📄 Fluxo Panfleto (Ingestion)](./fluxo-dominios-panfleto.md)
- [📄 Fluxo Analytics](./fluxo-dominios-analytics.md)
- [📄 Fluxo Identity & Privacy](./fluxo-dominios-identity.md)

## 🧩 Diagrama

```mermaid
flowchart LR

subgraph C ["Eventos de Entrada"]
  E1["CupomProcessado"]
  E2["PanfletoProcessado"]
end

subgraph P ["Dominio Pricing"]
  P1["Enfileirar price.update"]
  P2["Comparar com preco atual"]
  P3["Atualizar se diferente"]
  P4["Registrar origem (cupom/panfleto/manual)"]
  P5["Publicar PriceUpdatedEvent"]
end

subgraph R ["Read Models & Cache"]
  R1["Atualizar visao de leitura"]
  R2["Atualizar Redis (price:storeId:productId)"]
end

subgraph A ["Analytics"]
  A1["Log de atualizacoes"]
  A2["Metricas agregadas"]
end

E1 --> P1
E2 --> P1
P1 --> P2 --> P3 --> P4 --> P5
P5 --> R1 --> R2
P5 --> A1 --> A2

%% estilos
style P fill:#f7fff0,stroke:#3b7d3b,stroke-width:1px
style R fill:#eef7ff,stroke:#3b7ddd,stroke-width:1px
style A fill:#fff8dc,stroke:#8b6f00,stroke-width:1px
```

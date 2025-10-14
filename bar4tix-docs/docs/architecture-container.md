# C4 — Containers/Serviços

```mermaid
flowchart TB
  GW[Traefik API Gateway]
  BFF[comparison-service (WebFlux)]
  INJ[ingestion-service]
  OCR[crawler-ocr-worker]
  CAT[catalog-service]
  PRC[pricing-service]
  PLC[places-service]
  BI[bi-service]
  ID[identity-service]

  KF[(Kafka)]
  RD[(Redis)]
  PG[(PostgreSQL)]
  OBJ[(Object Storage)]

  GW --> BFF
  GW --> BI
  GW --> ID

  INJ -- eventos --> KF
  OCR -- arquivos --> OBJ
  INJ --> CAT
  INJ --> PRC

  BFF --> PRC
  BFF --> CAT
  BFF --> PLC

  PRC --> RD
  PRC --> PG
  CAT --> PG
  BI --> PG
  BI --> OBJ
```

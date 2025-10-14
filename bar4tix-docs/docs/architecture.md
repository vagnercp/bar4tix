# 🏗️ Arquitetura — Visão Geral

Stack alvo (evolutiva): **Java 21**, **Spring Boot 3.5.x (WebFlux)**, **Kafka**, **Redis**, **PostgreSQL**, **Traefik**, **Docker/K8s**.
Observabilidade: **Micrometer/OTLP**, **Prometheus**, **Grafana**, **Jaeger**.
Mobile/Web: **BFF WebFlux** → APIs *API‑first*.

## Estilo
- **Event‑Driven** + **API‑first**; **CQRS light** onde útil.
- **Pipelines de ingestão** desacoplados por tópicos/filas.
- **Cache de leitura** (Redis) para queries quentes (comparador).
- **Schema versionado** (Avro/JSON Schema) para eventos.
- **Multi‑tenant por “estabelecimento/região”** (feature flag futura).

## Serviços (macro)
- **ingestion-service**: QR NFC-e, panfletos (crawler + OCR), código de barras.
- **catalog-service**: produto/GTIN, normalização/dedup, categorias/marcas.
- **pricing-service**: consolidação de preços, regras de peso (cupom > panfleto).
- **comparison-service (BFF)**: busca/comparação e rotas de economia.
- **places-service**: check-in, geofencing, Google Places.
- **bi-service**: agregações, relatórios, export; API B2B.
- **identity-service**: contas, consentimentos LGPD, perfis e papéis.
- **gateway**: Traefik + rate‑limit, token e roteamento.

> **Regra de ouro**: *MVP enxuto*, dividir quando doer por *scale/ownership*.

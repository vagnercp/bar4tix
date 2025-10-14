# 🔌 Contratos de API (visão geral)

## BFF (público)
- `GET /v1/products/search?q=&near=`
- `GET /v1/compare?productId=&radius=`
- `POST /v1/receipt/scan` (payload NFC-e)
- `POST /v1/price/report` (barcode + price + check‑in)
- `GET /v1/stores/near?lat=&lng=&radius=`

## B2B/BI
- `GET /v1/bi/competition?storeId=`
- `GET /v1/bi/elasticity?category=`
- `GET /v1/bi/export?from=&to=&granularity=`

## Eventos (Kafka — esqueleto)
- `ingestion.receipt-scanned.v1`
- `ingestion.flyer-page.v1`
- `catalog.product-upserted.v1`
- `pricing.price-observed.v1`
- `pricing.price-consolidated.v1`

> **Nota**: Especificações detalhadas (OpenAPI/AsyncAPI) evoluirão por PRs.

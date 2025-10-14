# 📈 Métricas & Observabilidade

## Negócio
- MAU, % usuários com QR, N° itens atualizados/dia, cobertura por região.
- Acurácia por categoria/SKU; tempo médio de atualização de preço.
- Lojistas ativos; ARPA; churn.

## Técnica
- Latência P95 do comparador; taxa de erro por serviço.
- Tamanho e lag de tópicos Kafka; taxa de OCR/erros de parsing.
- Cache hit ratio; consumo de CPU/memória.

> Padronizar via Micrometer + OTLP; painéis no Grafana por domínio.

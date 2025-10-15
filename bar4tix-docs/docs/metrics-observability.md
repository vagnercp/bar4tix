# 📈 Métricas & Observabilidade — Bar4tix (v0.2)

## 🎯 Objetivo

Estabelecer um modelo unificado de métricas, logs e traços distribuídos para monitorar:

* performance técnica e confiabilidade dos serviços;
* impacto direto no negócio (dados, acurácia e engajamento);
* qualidade e completude das fontes de preço;
* governança de dados e integridade das pipelines.

Base: **Micrometer + OTLP → Prometheus + Grafana + Jaeger + Loki**

---

## 🧩 Estrutura de Observabilidade

| Camada                   | Propósito                                                 | Ferramentas              |
| ------------------------ | --------------------------------------------------------- | ------------------------ |
| **Aplicação**            | Expor métricas de negócio e técnicas via Micrometer       | Spring Boot Actuator     |
| **Infraestrutura**       | Coletar métricas de recursos (CPU, memória, disco)        | Prometheus Node Exporter |
| **Traços (Tracing)**     | Rastrear fluxos entre serviços (ingestão → pricing → BFF) | OpenTelemetry / Jaeger   |
| **Logs Estruturados**    | Registros JSON correlacionados com `traceId`, `spanId`    | Loki                     |
| **Painéis (Dashboards)** | Visualização de KPIs e SLIs/SLOs por domínio              | Grafana                  |

---

## 🧠 Domínios de Métricas

### 1. **Negócio (Product Intelligence)**

| Indicador                               | Descrição                                                 | Fonte                    | Tipo         |
| --------------------------------------- | --------------------------------------------------------- | ------------------------ | ------------ |
| **MAU (Monthly Active Users)**          | Usuários únicos ativos no app/web no mês                  | identity / app analytics | Contador     |
| **% Usuários com QR/NFC-e**             | Percentual de usuários que enviaram pelo menos um cupom   | coupon / ingest          | Razão        |
| **Itens Atualizados/dia**               | Quantidade média de observações de preço novas por dia    | price.observations       | Taxa         |
| **Cobertura por Região**                | % de produtos com observações em cada `region_id`         | price.current            | Proporção    |
| **Acurácia por Categoria/SKU**          | Validação de consistência dos preços (manual + algoritmo) | bi-service               | Score        |
| **Tempo Médio de Atualização de Preço** | Δ entre captura e consolidação final                      | ingestion → pricing      | Latência (s) |
| **Lojistas Ativos**                     | Contagem de lojas com atualizações recentes               | market.stores            | Contador     |
| **ARPA (Average Revenue per Account)**  | Receita média por cliente B2B                             | billing                  | Monetização  |
| **Churn B2B**                           | Lojas canceladas/sem uso em 30d                           | identity + usage logs    | Taxa         |

---

### 2. **Dados e Qualidade (DataOps)**

| Indicador                          | Descrição                                         | Meta   | Origem             |
| ---------------------------------- | ------------------------------------------------- | ------ | ------------------ |
| **% de GTIN válidos**              | Produtos com GTIN reconhecido                     | ≥ 95%  | catalog-service    |
| **Match Rate OCR x Catálogo**      | Taxa de sucesso do OCR ao mapear produtos válidos | ≥ 90%  | ingestion-service  |
| **Erro de Parsing OCR (%)**        | Falhas de extração por lote                       | ≤ 5%   | ingestion-service  |
| **Duplicatas Detectadas (%)**      | Produtos duplicados mesclados pelo dedup          | ≤ 3%   | catalog-service    |
| **Anomalias de Preço/dia**         | Registros fora da faixa padrão                    | —      | pricing-service    |
| **Lag Médio de Ingestão (s)**      | Tempo médio ingestão → evento consolidado         | ≤ 60 s | Kafka / OTLP spans |
| **Preço sem Atualização > X dias** | Percentual de produtos sem atualização            | ≤ 10%  | price.current      |

---

### 3. **Técnica / Engenharia**

| Métrica                           | Descrição                                        | Tipo    | Alerta     |
| --------------------------------- | ------------------------------------------------ | ------- | ---------- |
| **HTTP Latency P95/P99**          | Tempo de resposta do comparador (cache hit/miss) | Timer   | >800 ms    |
| **Taxa de Erro HTTP (5xx)**       | Erros por rota e serviço                         | Counter | >2%        |
| **Kafka Lag**                     | Atraso médio por partição                        | Gauge   | >5000 msgs |
| **Kafka Throughput**              | Taxa de mensagens processadas                    | Counter | —          |
| **OCR Processing Time**           | Tempo médio de OCR por imagem                    | Timer   | >5 s       |
| **Cache Hit Ratio (Redis)**       | % de acertos no cache                            | Gauge   | <85%       |
| **CPU/Memory Usage**              | Consumo por pod                                  | Gauge   | >80% CPU   |
| **DB Connections / Slow Queries** | Atividade e gargalos do Postgres                 | Timer   | >2 s/query |
| **Queue Size (OCR Jobs)**         | Jobs pendentes na fila de OCR                    | Gauge   | >200       |
| **Uptime per Service**            | Disponibilidade acumulada (rolling 30d)          | Counter | <99.9%     |

---

### 4. **Infraestrutura & Segurança**

| Métrica                     | Descrição                                                 | Origem             |
| --------------------------- | --------------------------------------------------------- | ------------------ |
| **Rate Limit Triggered**    | Número de requisições bloqueadas por limitação de tráfego | Traefik            |
| **Auth Success/Fail**       | Taxa de autenticações válidas/inválidas                   | gateway / identity |
| **Pod Restarts**            | Reinícios de contêineres                                  | K8s metrics        |
| **Deployment Success Rate** | % de deploys sem rollback                                 | CI/CD pipeline     |
| **Disk Usage & IOPS**       | Uso de disco e performance de storage                     | Node exporter      |
| **TLS Renewal**             | Status dos certificados (Let's Encrypt)                   | Traefik metrics    |

---

### 5. **Observabilidade e Correlation**

**Correlações via OTLP:**

* `traceId` propaga entre: ingestion → catalog → pricing → comparison.
* `span.name`: indica a etapa (`ocr.process`, `price.consolidate`, `compare.query`).
* **Logs estruturados**: JSON com `traceId`, `spanId`, `service`, `level`, `message`.

**Micrometer naming convention:**
`bar4tix.<domínio>.<métrica>`
Exemplo:

* `bar4tix.price.consolidation.latency_seconds`
* `bar4tix.catalog.match_rate`
* `bar4tix.ocr.failure_ratio`

---

## 🧮 SLOs e SLIs

| SLI                                           | SLO (Meta)                     | Tipo            |
| --------------------------------------------- | ------------------------------ | --------------- |
| **Disponibilidade BFF**                       | ≥ 99.9% / mês                  | Disponibilidade |
| **Latência P95 Comparador**                   | ≤ 250 ms (hit) / 800 ms (miss) | Desempenho      |
| **Freshness de Preço (T_ingest → T_display)** | ≤ 60 s                         | Tempo de Dado   |
| **Erro OCR (%)**                              | ≤ 5%                           | Qualidade       |
| **Lag Kafka P99**                             | ≤ 5 s                          | Pipeline        |
| **Cache Hit Ratio**                           | ≥ 85%                          | Eficiência      |
| **Acurácia de Preço**                         | ≥ 95%                          | Dados           |
| **Tempo de Recuperação (MTTR)**               | ≤ 15 min                       | Resiliência     |

---

## 🧱 Padrões de Implementação (Micrometer + OTLP)

```kotlin
class PricingMetrics(private val meterRegistry: MeterRegistry) {
    fun recordConsolidationTime(duration: Duration) =
        Timer.builder("bar4tix.pricing.consolidation.time")
            .description("Tempo de consolidação de preço")
            .publishPercentiles(0.5, 0.95, 0.99)
            .register(meterRegistry)
            .record(duration)
}
```

* **Prefixo padrão:** `bar4tix.<domínio>.<ação>.<métrica>`
* **Tagging padrão:** `region`, `store`, `source`, `status`, `env`.
* **Export OTLP:** via `management.otlp.metrics.export.enabled=true`.

---

## 🧭 Painéis no Grafana (recomendações)

| Painel                | Objetivo                 | Principais Gráficos                    |
| --------------------- | ------------------------ | -------------------------------------- |
| **Business Overview** | Engajamento e cobertura  | MAU, itens atualizados, QR uploads     |
| **Data Quality**      | Confiabilidade dos dados | Acurácia, erros OCR, lag Kafka         |
| **Service Health**    | Performance técnica      | P95 latency, CPU/mem, erro HTTP        |
| **Cache & Redis**     | Eficiência de cache      | hit ratio, latência Redis              |
| **Pricing Accuracy**  | Validação de preços      | anomalias, desvio padrão por categoria |
| **Infra Overview**    | Visão DevOps             | Pods, CPU/mem, deploy success, TLS     |

---

## 🧩 Alertas e Notificações

| Categoria       | Trigger                                 | Ação                           |
| --------------- | --------------------------------------- | ------------------------------ |
| **Performance** | Latência > 800 ms por 5 min             | Alerta Slack #ops              |
| **Dados**       | Acurácia < 90% por 24 h                 | Notificação DataOps            |
| **Infra**       | Pod restart > 5 em 10 min               | PagerDuty                      |
| **Kafka**       | Lag > 5 000 mensagens                   | Auto-scale consumidor          |
| **OCR**         | Falhas > 10% lote                       | Reprocessamento automático     |
| **LGPD/PCI**    | Consentimento expirado / Token inválido | Auditoria + bloqueio transação |

---

## 🧠 Governança de Observabilidade

* Cada serviço define seu owner (`@team("pricing")` etc).
* KPIs revisados trimestralmente pelo **Data & SRE Council**.
* Métricas de Negócio alimentam BI; Técnicas suportam SLOs e SLAs.
* Logs sensíveis passam por ofuscação e retenção limitada (90 dias).
* Observabilidade ≠ Telemetria: foco em causalidade, não apenas coleta.

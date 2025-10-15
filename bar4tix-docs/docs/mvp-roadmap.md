# 🚀 MVP & Roadmap — Bar4tix (v0.3)

## 🎯 Visão Geral

O roadmap equilibra entregas incrementais com maturidade de dados e observabilidade, garantindo que cada fase avance em três eixos:

* **Produto** (experiência e valor percebido)
* **Dados** (escala, qualidade e governança)
* **Operação** (observabilidade, confiabilidade e segurança)

---

## 🧩 Fase 1 — Validação (0–3 meses)

**Objetivo:** Provar o conceito e validar o ciclo de dados colaborativos (QR + check-in + comparador).

### Entregas Principais

* App/Web MVP com:

  * Captura via QR da NFC-e e leitura de código de barras.
  * Comparador básico (busca + preços próximos).
  * Check-in geolocalizado vinculado à loja.
* Redis + PostgreSQL configurados.
* Ingestão manual de preços (cupom/CSV).
* Painel inicial de métricas (Grafana) com dados agregados.

### Métricas de Sucesso

| Indicador                           | Meta                         |
| ----------------------------------- | ---------------------------- |
| **MAU**                             | ≥ 1.000 usuários ativos      |
| **% notas válidas (QR)**            | ≥ 80%                        |
| **Cobertura por bairro**            | ≥ 60% lojas da cidade piloto |
| **Tempo médio ingestão → exibição** | ≤ 30s                        |

### Marcos Técnicos

* Observabilidade básica (Micrometer + Prometheus).
* Política de dados LGPD inicial.
* 1ª release no Traefik/K8s.
* CI/CD com testes unitários.

---

## 📊 Fase 2 — Escala de Dados (3–6 meses)

**Objetivo:** Aumentar volume e diversidade de fontes de dados; elevar acurácia.

### Entregas Principais

* Ingestão automática de panfletos (crawler + OCR).
* Deduplicação e normalização de catálogos (GTIN + heurística).
* Regras de peso e qualidade (`cupom > panfleto > manual`).
* Consolidação em `price.current` + cache Redis.
* Monitoramento Kafka (lag, throughput).
* Dashboards DataOps (acurácia, erro OCR, lag médio).

### Métricas de Sucesso

| Indicador                         | Meta  |
| --------------------------------- | ----- |
| **Acurácia OCR x catálogo**       | ≥ 90% |
| **Recall OCR**                    | ≥ 85% |
| **Tempo médio atualização preço** | ≤ 60s |
| **Match de GTIN válidos**         | ≥ 95% |

### Marcos Técnicos

* Pipeline Kafka (ingestão → pricing).
* Monitoramento OTLP/Jaeger.
* `data-model-v0.2` implementado.
* Compressão e cache inteligente no Redis.

---

## 📈 Fase 3 — BI & Inteligência (6–9 meses)

**Objetivo:** Gerar valor analítico para lojistas e parceiros (B2B/B2G).

### Entregas Principais

* Dashboards B2B (concorrência, variação de preço, elasticidade).
* API B2B (feeds regionais e relatórios exportáveis).
* Alertas automáticos (anomalias de preço).
* Métricas de performance financeira (ARPA, churn, LTV/CAC).
* Módulo de auditoria (`audit.events`).

### Métricas de Sucesso

| Indicador                      | Meta                     |
| ------------------------------ | ------------------------ |
| **Lojistas ativos (B2B)**      | ≥ 100                    |
| **Churn B2B**                  | ≤ 10%                    |
| **ARPA**                       | ≥ R$ 200/mês             |
| **Elasticidade mapeada (SKU)** | ≥ 70% das categorias top |

### Marcos Técnicos

* `bi-service` e `price.mv_current` operacionais.
* Export de dados anonimizados (Trusted Zone).
* Integração Grafana ↔ Metabase.
* SLIs/SLOs definidos para serviços core.

---

## 🌍 Fase 4 — Expansão & Monetização (9–12 meses)

**Objetivo:** Escalar a operação regionalmente e validar modelos de monetização.

### Entregas Principais

* Novas cidades e clusters regionais.
* Parcerias locais (redes, distribuidores, prefeituras).
* Integrações ERP/CRM.
* Ads e promoções geolocalizadas.
* Módulo **Cashback Wallet** (PCI-safe).
* Política de anonimização + compliance PCI/LGPD.

### Métricas de Sucesso

| Indicador                         | Meta        |
| --------------------------------- | ----------- |
| **Regiões ativas**                | ≥ 5         |
| **Tempo médio onboarding loja**   | ≤ 2 dias    |
| **Volume de cashback processado** | ≥ R$ 50.000 |
| **Latência média PSP (PCI)**      | ≤ 300ms     |
| **Taxa de erro PSP**              | ≤ 0.1%      |

### Marcos Técnicos

* PCI Zone e integração com PSP.
* Módulo `wallet-service` com observabilidade dedicada.
* Pipeline de eventos financeiros instrumentado (Micrometer + OTLP).
* Auditoria de transações + antifraude.

---

## 🧭 Fase 5 — Sustentação e Escala Nacional (12–18 meses)

**Objetivo:** Consolidar Bar4tix como infraestrutura de dados de preços e consumo.

### Entregas Principais

* Marketplace de dados (API de insights regionais).
* Parcerias com universidades e órgãos públicos.
* Recomendador de produtos por elasticidade.
* Monitor de inflação local em tempo real.
* Versionamento de modelos IA/OCR.
* Expansão observabilidade (SLO por domínio).

### Métricas de Sucesso

| Indicador                               | Meta               |
| --------------------------------------- | ------------------ |
| **Cobertura nacional**                  | ≥ 50% das capitais |
| **Tempo de atualização média nacional** | ≤ 90s              |
| **Acurácia de modelos IA/OCR**          | ≥ 97%              |
| **SLA de serviços core**                | ≥ 99.95%           |

---

## 🧠 Roadmap Transversal (Fundamentos)

| Pilar                   | Entregas contínuas                                                           |
| ----------------------- | ---------------------------------------------------------------------------- |
| **LGPD & Privacidade**  | Políticas de consentimento, dashboard “Meus Dados”, anonimização automática. |
| **Observabilidade**     | SLIs/SLOs, logs estruturados, painéis Grafana e alertas automáticos.         |
| **Governança de Dados** | Dicionário de dados, scoring de qualidade, Trusted Data Zone.                |
| **Segurança & PCI**     | Zoneamento PCI, tokenização, auditoria de pagamentos e carteiras.            |
| **Infra & DevOps**      | CI/CD, testes automatizados, blue-green deploy, autoscaling.                 |
| **IA & OCR**            | Evolução contínua de modelos (embeddings, matching, dedup).                  |

---

## ⚠️ Riscos & Mitigações

| Categoria                        | Risco                                                 | Impacto | Mitigação                                                        |
| -------------------------------- | ----------------------------------------------------- | ------- | ---------------------------------------------------------------- |
| **Dados (OCR)**                  | Baixa acurácia de OCR em panfletos de baixa qualidade | Médio   | Ajuste de modelos OCR, reprocessamento e revisão manual limitada |
| **Qualidade de Preços**          | Divergência entre fontes (cupom vs panfleto)          | Alto    | Ponderação dinâmica (source weight) + validação por amostragem   |
| **Adoção de Lojistas (B2B)**     | Baixa adesão inicial de parceiros                     | Alto    | Campanhas regionais + dashboards gratuitos de benchmarking       |
| **Infraestrutura (Kafka/Redis)** | Gargalo de ingestão em picos                          | Alto    | Auto-scaling horizontal + circuit breakers + fila buffer         |
| **Compliance PCI/LGPD**          | Falhas de anonimização ou tokenização                 | Crítico | Segregação de ambientes PCI, auditoria periódica, vault dedicado |
| **Custo de Cloud**               | Crescimento rápido de storage e OCR                   | Médio   | Compressão, retenção programada e política de arquivamento frio  |
| **Manutenção de IA**             | Modelos obsoletos com novos layouts de panfletos      | Médio   | Retraining trimestral + base de teste rotulada                   |
| **Equipe/Recursos**              | Dependência alta de times técnicos específicos        | Médio   | Documentação de processos + automação CI/CD                      |
| **Experiência do Usuário**       | Latência elevada no comparador                        | Médio   | Redis caching + indexação incremental + otimização WebFlux       |

---

**Versão:** v0.3
**Autor:** Vagner Coelho Pinto
**Data:** 2025-10-14
**Licença:** CC-BY-NC-SA 4.0

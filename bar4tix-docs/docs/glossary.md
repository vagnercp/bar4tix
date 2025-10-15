# 📒 Glossário — Bar4tix (v0.2)

## 🔠 Identificadores e Documentos
| Termo | Definição |
|--------|------------|
| **NFC-e** | Nota Fiscal de Consumidor eletrônica; contém QR code com chave de consulta pública, usada para validar preços reais. |
| **GTIN** | Global Trade Item Number (código de barras); identifica unicamente um produto comercial. |
| **SKU** | Stock Keeping Unit; variação específica de um produto (GTIN + embalagem + sabor). |
| **CNPJ Hash** | Identificador anonimizado de uma loja, derivado do CNPJ via hash seguro. |

---

## 📈 Métricas e Indicadores
| Termo | Definição |
|--------|------------|
| **Elasticidade de Preço** | Medida que indica quanto a demanda muda em função da variação do preço. |
| **Quality Score** | Índice de confiança calculado com base na qualidade da fonte (OCR, check-in, frequência e recência). |
| **Source Weight** | Peso de importância da origem do dado; regra padrão: cupom > manual > panfleto. |
| **Confidence** | Nível de certeza consolidado sobre o preço final (0 a 1). |
| **Acurácia de Preço** | Percentual de observações que correspondem ao preço real verificado. |
| **Freshness** | Tempo entre a captura e a disponibilização do dado. |
| **Lag Kafka** | Atraso médio de consumo de eventos (ms ou mensagens). |

---

## 🧩 Conceitos de Dados
| Termo | Definição |
|--------|------------|
| **Product Canonical** | Versão única e normalizada de um produto, independentemente de variações de nome ou marca. |
| **Alias** | Nome alternativo identificado via OCR ou panfleto; vinculado ao produto canônico. |
| **Observation (Preço)** | Registro individual de preço por loja e produto; base dos cálculos de consolidação. |
| **Price Current** | Tabela/visão materializada com o preço mais confiável e recente. |
| **Region ID** | Identificador lógico de agrupamento geográfico (ex: `uberlandia-mg`). |
| **Event ID** | Identificador único de evento (Kafka); garante idempotência. |
| **Data Zone (Trusted)** | Camada de dados já validados e anonimizados, prontos para uso analítico. |

---

## 🧠 Inteligência e Processamento
| Termo | Definição |
|--------|------------|
| **OCR (Reconhecimento Óptico de Caracteres)** | Processo de leitura automática de textos de panfletos e cupons. |
| **Crawler** | Robô que coleta automaticamente panfletos e promoções em sites de varejistas. |
| **Matching Engine** | Algoritmo que correlaciona diferentes fontes (OCR, QR, API) para o mesmo produto. |
| **Consolidação de Preços** | Combinação ponderada das fontes para definir o preço mais confiável. |
| **Anomalia de Preço** | Desvio detectado estatisticamente (queda ou alta abrupta). |
| **Pipeline de Ingestão** | Fluxo que captura, normaliza e distribui eventos de dados (OCR → Kafka → Redis). |
| **Flywheel de Dados** | Efeito de rede: quanto mais dados e usuários → maior precisão e valor → mais engajamento. |

---

## 🏪 Domínios e Contextos
| Termo | Definição |
|--------|------------|
| **B2C** | Interações com consumidores finais (app/web). |
| **B2B** | Serviços e relatórios para lojistas e indústrias. |
| **B2G** | Relatórios e insights disponibilizados a órgãos públicos. |
| **Check-in** | Registro de presença do usuário em um local; confirma vínculo entre pessoa e loja. |
| **Store Region** | Cluster geográfico de lojas (bairro, cidade, microrregião). |

---

## 🔐 Privacidade e LGPD
| Termo | Definição |
|--------|------------|
| **PII (Personally Identifiable Information)** | Dados que identificam diretamente um indivíduo (ex: e-mail, CPF). |
| **PII Indireta** | Dados que, combinados, podem identificar (ex: geolocalização + data + loja). |
| **Consent Scope** | Escopo de autorização (analytics, marketing, cashback). |
| **Anonimização** | Processo irreversível de transformação de dados pessoais em não identificáveis. |
| **Hash + Sal** | Técnica de ofuscação de dados onde um valor secreto (sal) é adicionado antes do hash. |
| **Revogação** | Direito do usuário de retirar o consentimento e solicitar exclusão. |

---

## ⚙️ Infraestrutura e Observabilidade
| Termo | Definição |
|--------|------------|
| **Traefik** | Gateway reverso que faz roteamento, autenticação e rate-limiting. |
| **Kafka** | Sistema de mensagens/eventos usado para ingestão e distribuição assíncrona. |
| **Redis** | Cache em memória para consultas rápidas (comparador). |
| **Prometheus/Grafana/Jaeger** | Stack de observabilidade (métricas, visualização, rastreamento). |
| **Microservice / BFF** | Serviço independente responsável por uma funcionalidade (BFF = camada de orquestração). |
| **CQRS Light** | Separação leve entre comandos (escrita) e consultas (leitura). |

---

## 🧮 Indicadores de Negócio
| Termo | Definição |
|--------|------------|
| **MAU (Monthly Active Users)** | Número de usuários ativos mensais. |
| **LTV (Lifetime Value)** | Valor total gerado por um cliente durante seu ciclo de vida. |
| **CAC (Customer Acquisition Cost)** | Custo médio para adquirir um novo cliente. |
| **Churn** | Taxa de cancelamento ou inatividade. |
| **MRR (Monthly Recurring Revenue)** | Receita recorrente mensal de planos B2B. |


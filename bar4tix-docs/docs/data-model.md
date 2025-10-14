# 📦 Bar4tix — Modelo de Dados (Visão de Negócio & Privacidade)

## 🧬 Núcleo Conceitual

| Domínio | Objetivo | Tipo de dado |
|----------|-----------|--------------|
| **Produto (`prdt`)** | Identificar e descrever o item comparado entre fontes. | Metadados públicos (não pessoais). |
| **Mercado (`market`)** | Representar lojas, redes e regiões de venda. | Sem PII (dados corporativos). |
| **Preço (`price`)** | Registrar e consolidar observações de preço por produto e loja. | Dados analíticos derivados. |
| **Cupom (`coupon`)** | Capturar compras reais de consumidores (prova de preço). | PII indireta (via hash). |
| **Locais (`places`)** | Representar check-ins e posição de lojas. | Dados de localização sensíveis. |
| **Identidade (`identity`)** | Gerenciar usuários, consentimentos e preferências. | PII direta. |
| **Ingestão (`ingest`)** | Controlar origem e status dos dados coletados. | Dados técnicos. |
| **Auditoria (`audit`)** | Rastrear alterações e correções manuais. | Logs internos. |

---

## 🧠 Entidades e Informações-Chave

### 1. **Produto (`prdt`)**
- **Finalidade:** Padronizar nomes e atributos de produtos coletados via OCR, QR ou APIs.
- **Informar:** nome, GTIN/EAN, marca, categoria, unidade, atributos (volume, peso, sabor, tipo), qualidade de identificação.
- **Sensibilidade:** Pública.
- **Uso:** base de comparação e BI regional.

### 2. **Marca e Categoria (`brands`, `categories`)**
- **Finalidade:** Organizar e hierarquizar produtos.
- **Informar:** nome, hierarquia, aliases.
- **Sensibilidade:** Pública.
- **Uso:** relatórios e filtros de busca.

### 3. **Lojas e Regiões (`market`)**
- **Finalidade:** Representar pontos de venda e regiões.
- **Informar:** nome, rede, endereço aproximado, coordenadas, hash do CNPJ.
- **Sensibilidade:** corporativa (não PII).
- **Uso:** ranking de preços e análise regional.

### 4. **Preços (`price`)**
- **Finalidade:** Consolidar valores observados por produto e loja.
- **Informar:** valor, moeda, fonte (cupom, panfleto, manual), confiança (peso da fonte, OCR, recência).
- **Regras:** cupom > panfleto > manual.
- **Sensibilidade:** baixa.
- **Uso:** comparador e BI.

### 5. **Cupons (`coupon`)**
- **Finalidade:** Provar preços reais observados.
- **Informar:** hash de CPF, loja, data/hora, total, produtos (quantidade, preço unitário), JSON bruto.
- **Sensibilidade:** alta (origina de documento fiscal pessoal).
- **Governança:** hash + sal externo, consentimento obrigatório, retenção 24 meses.
- **Uso:** validação e aprendizado de modelos.

### 6. **Check-ins (`places`)**
- **Finalidade:** Validar presença do usuário no local da compra.
- **Informar:** coordenadas, loja/região, timestamp, precisão.
- **Sensibilidade:** alta (localização pessoal).
- **Governança:** opt-in explícito, arredondamento de coordenadas, retenção curta (6-12 meses).
- **Uso:** validação de contexto.

### 7. **Usuários (`identity`)**
- **Finalidade:** Representar perfis, consentimentos e preferências.
- **Informar:** UUID, hashes de e-mail/telefone, consentimentos, papéis.
- **Sensibilidade:** alta (PII).
- **Governança:** hash + consentimento, escopos claros, retenção limitada.
- **Uso:** autenticação e LGPD.

### 8. **Ingestão (`ingest`)**
- **Finalidade:** Controlar fluxo de coleta (OCR, crawler, QR).
- **Informar:** ID de origem, tipo, status, tentativas, metadados.
- **Sensibilidade:** baixa.
- **Uso:** rastreabilidade técnica.

### 9. **Auditoria (`audit`)**
- **Finalidade:** Garantir rastreabilidade de alterações humanas.
- **Informar:** quem, o quê, antes/depois, timestamp.
- **Sensibilidade:** interna.
- **Uso:** compliance e qualidade de dados.

---

## 🔏 Classificação de Sensibilidade

| Categoria | Exemplo | Sensibilidade | Proteção |
|------------|----------|---------------|-----------|
| **Pública** | Produto, marca, categoria, preço agregado | Baixa | Nenhuma |
| **Corporativa** | Loja, rede, CNPJ hash, região | Média | Hash/criptografia em repouso |
| **PII direta** | E-mail, CPF, telefone | Alta | Hash + consentimento + retenção limitada |
| **PII indireta** | Geo, timestamp de compra, correlação cupom-loja | Alta | Anonimização, arredondamento, amostragem |
| **Técnica** | Logs, jobs, métricas | Baixa | Retenção curta, acesso restrito |

---

## 🔒 Governança e Privacidade (LGPD+)

| Tema | Prática |
|------|----------|
| **Minimização** | Coletar apenas o necessário; remover metadados pessoais. |
| **Anonimização** | Hash de CPF e e-mail com sal rotativo. |
| **Consentimento** | Escopos separados: análise, marketing, cashback. |
| **Revogação** | Exclusão lógica e física em 30 dias. |
| **Retenção** | Cupom e check-in ≤ 24 meses; logs ≤ 90 dias. |
| **Transparência** | Dashboard “Meus Dados” (app/web). |
| **Compartilhamento** | Apenas dados agregados (produto, loja, região). |

---

## 📊 Perspectivas de Uso

| Perspectiva | Dados utilizados | Finalidade |
|--------------|------------------|-------------|
| **B2C (App)** | `prdt`, `market`, `price.current` | Comparar preços e economizar. |
| **B2B (Dashboard)** | `price.observations`, `market`, `bi` | Inteligência competitiva. |
| **B2G (Município)** | `price.current` agregado | Monitorar inflação local. |
| **Interna (Engenharia/IA)** | `ingest`, `coupon`, `checkins` anonimizados | Treinar OCR, detectar anomalias. |

---

## 🤖 Próximos Passos

1. Definir **dicionário de dados sensíveis** (campo, owner, finalidade, retenção).
2. Mapear **linhagem de dados** (coleta → processamento → BI).
3. Criar **Trusted Data Zone** (dados já anonimizados e auditados). 
4. Implementar **data scoring** (qualidade, completude, confiabilidade).  
5. Formalizar **Data Governance Playbook** (owners, acessos, revisões).

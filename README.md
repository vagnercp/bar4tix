# 🧱 Bar4tix — Visão de Negócio e Arquitetura *(Living Docs)*

Este repositório versiona a **visão de negócio** e a **visão arquitetural** da plataforma **Bar4tix**.
Os documentos são *vivos* e evoluem junto com o produto — cada mudança deve ter um PR curto e objetivo.

---

## ⚙️ Como trabalhar

1. Edite o(s) arquivo(s) `.md` de uma única “coisa” por PR.
2. Use títulos e checklists para sinalizar o status de cada seção.
3. Ligue decisões a issues/PRs e registre no diretório [`docs/decisions/`](docs/decisions/) (ADR leve).

---

## 📂 Estrutura

| Documento                                                          | Descrição                                               |
| ------------------------------------------------------------------ | ------------------------------------------------------- |
| [`docs/business-model.md`](docs/business-model.md)                 | Lean/Business Model Canvas e monetização                |
| [`docs/product-vision.md`](docs/product-vision.md)                 | Visão de produto e princípios                           |
| [`docs/personas.md`](docs/personas.md)                             | Personas & Jornadas (UX)                                |
| [`docs/mvp-roadmap.md`](docs/mvp-roadmap.md)                       | MVP, fases e escopo (com OKRs iniciais)                 |
| [`docs/okrs.md`](docs/okrs.md)                                     | OKRs por trimestre (produto e tech)                     |
| [`docs/architecture.md`](docs/architecture.md)                     | Visão geral da arquitetura (C4 leve)                    |
| [`docs/architecture-context.md`](docs/architecture-context.md)     | Contexto do sistema (C4-Context)                        |
| [`docs/architecture-container.md`](docs/architecture-container.md) | Containers, serviços e integrações                      |
| [`docs/data-model.md`](docs/data-model.md)                         | Modelo de dados lógico e diretrizes de schema           |
| [`docs/api-contracts.md`](docs/api-contracts.md)                   | Contratos de API (alta visão + exemplos)                |
| [`docs/data-pipeline.md`](docs/data-pipeline.md)                   | Ingestão (cupom, panfleto/OCR, check-in), qualidade, BI |
| [`docs/security-privacy.md`](docs/security-privacy.md)             | Segurança, LGPD, anonimização e papéis                  |
| [`docs/metrics-observability.md`](docs/metrics-observability.md)   | Métricas (negócio e técnica), logs e traces             |
| [`docs/glossary.md`](docs/glossary.md)                             | Termos, abreviações e definições                        |
| [`docs/decisions/`](docs/decisions/)                               | ADRs leves (Arquitetura / Produto)                      |

---

## 📈 Status

* [x] Estrutura inicial criada
* [x] Links de navegação adicionados
* [ ] Preencher lacunas marcadas com `TODO` via PRs incrementais
* [ ] Criar visualizações C4 (Context + Container)
* [ ] Adicionar seção “Plano de Resposta a Incidentes” em `security-privacy.md`

---

## 📘 Próximos Passos

| Objetivo            | Ação                                                                    | Responsável     |
| ------------------- | ----------------------------------------------------------------------- | --------------- |
| **Diagramas C4**    | Gerar visão Context e Container detalhada                               | Arquitetura     |
| **ADRs iniciais**   | Criar 3 ADRs leves: cache dinâmico, pipeline OCR, e gateway de métricas | Tech Lead       |
| **API Contracts**   | Documentar payloads e schemas de resposta                               | Backend         |
| **UX Research**     | Adicionar cenários de validação e feedbacks reais                       | Produto / UX    |
| **Segurança**       | Definir e versionar plano de resposta a incidentes                      | Sec/DevOps      |
| **Observabilidade** | Consolidar dashboards Grafana e painéis Jaeger                          | Eng. Plataforma |

---

**Versão:** v2025.10
**Autor:** Vagner Coelho Pinto
**Licença:** CC-BY-NC-SA 4.0


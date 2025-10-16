# 🧱 Bar4tix — Visão de Negócio e Arquitetura *(Living Docs)*

Este repositório versiona a **visão de negócio** e a **visão arquitetural** da plataforma **Bar4tix**.
Os documentos são *vivos* e evoluem junto com o produto — cada mudança deve ter um PR curto e objetivo.

---

## ⚙️ Como trabalhar

1. Edite o(s) arquivo(s) `.md` de uma única “coisa” por PR.
2. Use títulos e checklists para sinalizar o status de cada seção.
3. Ligue decisões a issues/PRs e registre no diretório [`docs/decisions/template`](bar4tix-docs/docs/decisions) (ADR leve).

---

## 📂 Estrutura

| Documento                                                                       | Descrição                                               |
|---------------------------------------------------------------------------------| ------------------------------------------------------- |
| [`docs/business-model.md`](bar4tix-docs/docs/business-model.md)                 | Lean/Business Model Canvas e monetização                |
| [`docs/product-vision.md`](bar4tix-docs/docs/product-vision.md)                 | Visão de produto e princípios                           |
| [`docs/personas.md`](bar4tix-docs/docs/personas.md)                             | Personas & Jornadas (UX)                                |
| [`docs/mvp-roadmap.md`](bar4tix-docs/docs/mvp-roadmap.md)                       | MVP, fases e escopo (com OKRs iniciais)                 |
| [`docs/okrs.md`](bar4tix-docs/docs/okrs.md)                                     | OKRs por trimestre (produto e tech)                     |
| [`docs/architecture.md`](bar4tix-docs/docs/architecture.md)                     | Visão geral da arquitetura (C4 leve)                    |
| [`docs/architecture-context.md`](bar4tix-docs/docs/architecture-context.md)     | Contexto do sistema (C4-Context)                        |
| [`docs/architecture-container.md`](bar4tix-docs/docs/architecture-container.md) | Containers, serviços e integrações                      |
| [`docs/data-model.md`](bar4tix-docs/docs/data-model.md)                         | Modelo de dados lógico e diretrizes de schema           |
| [`docs/api-contracts.md`](bar4tix-docs/docs/api-contracts.md)                   | Contratos de API (alta visão + exemplos)                |
| [`docs/data-pipeline.md`](bar4tix-docs/docs/data-pipeline.md)                   | Ingestão (cupom, panfleto/OCR, check-in), qualidade, BI |
| [`docs/security-privacy.md`](bar4tix-docs/docs/security-privacy.md)             | Segurança, LGPD, anonimização e papéis                  |
| [`docs/metrics-observability.md`](bar4tix-docs/docs/metrics-observability.md)   | Métricas (negócio e técnica), logs e traces             |
| [`docs/glossary.md`](bar4tix-docs/docs/glossary.md)                             | Termos, abreviações e definições                        |
| [`docs/decisions/template`](bar4tix-docs/docs/decisions/ADR-000-template.md)    | ADRs leves (Arquitetura / Produto)                      |

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


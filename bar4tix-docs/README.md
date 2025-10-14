# Bar4tix — Visão de Negócio e Arquitetura (Living Docs)

Este repositório versiona a **visão de negócio** e a **visão arquitetural** da plataforma **Bar4tix**.
Os documentos são *vivos* (living docs) e evoluem junto com o produto. Cada mudança deve ter um PR curto e objetivo.

> Como trabalhar:
> 1. Edite o(s) arquivo(s) `.md` de uma única “coisa” por PR.
> 2. Use títulos e checklists para sinalizar status de cada seção.
> 3. Ligue decisões a issues/PRs e registre no `docs/decisions/` (ADR leve).

## Estrutura
- `docs/business-model.md` — Lean/Business Model Canvas e monetização
- `docs/product-vision.md` — Visão de produto e princípios
- `docs/personas.md` — Personas & jornadas (UX)
- `docs/mvp-roadmap.md` — MVP, fases e escopo (com OKRs iniciais)
- `docs/okrs.md` — OKRs por trimestre (produto e tech)
- `docs/architecture.md` — Visão geral da arquitetura (C4 leve)
- `docs/architecture-context.md` — Contexto do sistema (C4-Context)
- `docs/architecture-container.md` — Containers/serviços e integrações
- `docs/data-model.md` — Modelo de dados lógico e diretrizes de schema
- `docs/api-contracts.md` — Contratos de API (alta visão + exemplos)
- `docs/data-pipeline.md` — Ingestão (cupom, panfleto/OCR, check-in), qualidade, BI
- `docs/security-privacy.md` — Segurança, LGPD, anonimização, papéis
- `docs/metrics-observability.md` — Métricas (negócio e técnica), logs e traces
- `docs/decisions/` — ADRs leves (Arquitetura/Produto)
- `docs/glossary.md` — Termos, abreviações e definições

## Status
- [x] Estrutura inicial criada
- [ ] Preencher lacunas marcadas com `TODO` via PRs incrementais

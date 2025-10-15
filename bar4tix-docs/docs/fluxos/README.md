# 📊 Documentação de Fluxos — Bar4tix

> Este diretório reúne os **fluxos de domínio** do ecossistema Bar4tix.  
> Cada arquivo descreve, em nível **visual** (sem detalhes técnicos), o caminho dos dados e como os domínios interagem.

---

## 🧭 Índice

| Domínio | Descrição | Arquivo |
|----------|------------|----------|
| Receipts (QR-Code) | Captura e processamento de cupons fiscais | [fluxo-dominios-qrcode.md](./fluxo-dominios-qrcode.md) |
| Ingestion (Panfletos) | Coleta e extração de preços via OCR | [fluxo-dominios-panfleto.md](./fluxo-dominios-panfleto.md) |
| Pricing | Consolidação de preços e atualização de leitura | [fluxo-dominios-pricing.md](./fluxo-dominios-pricing.md) |
| Analytics | Geração de métricas e exportações | [fluxo-dominios-analytics.md](./fluxo-dominios-analytics.md) |
| Identity & Privacy | Governança, anonimização e segurança | [fluxo-dominios-identity.md](./fluxo-dominios-identity.md) |

---

## 🧩 Convenções
- Diagramas em **Mermaid** compatíveis com **GitHub**.
- Estrutura herdada entre fluxos (mesmo layout e nomenclatura).
- Cores discretas destacam domínios sensíveis (Identity).

---

## 🧰 Ferramentas Recomendadas (sem cadastro)
- [Mermaid Live Editor](https://mermaid.live) — preview e exportação PNG/SVG.
- [Mermaid Flow](https://www.mermaidflow.app/editor) — edição interativa.
- [Kroki.io Playground](https://kroki.io/#try) — renderização via URL.

---

## 🔒 Padrão de Rastreabilidade (alto nível)
- Dados pessoais ficam somente no domínio **Identity & Privacy**.
- Eventos e logs usam **identificadores anônimos** (`user_hash`).
- Somente o próprio usuário visualiza seus totais pessoais.

# 📦 Modelo de Dados (lógico, v0.1)

## Núcleo (schemas sugeridos)
- `prdt.` **products**(id, gtin, name, brand_id, unit_id, category_id, attrs)
- `prdt.` **brands**(id, name), **categories**(id, name, parent_id), **units**(id, code)
- `market.` **stores**(id, cnpj, name, place_id, address, geo)
- `price.` **observations**(id, product_id, store_id, source, price, currency, ts, quality_score, source_weight)
- `coupon.` **receipts**(id, cpf_hash, store_id, total, ts, raw_json), **items**(receipt_id, product_id, qty, unit_price)
- `places.` **checkins**(id, user_id, store_id, geo, ts, accuracy)
- `identity.` **users**(id, email, consent_flags, roles), **tenants**(id, name)

## Regras
- **Peso de fonte**: `cupom` > `barcode manual` > `panfleto/OCR`.
- **Qualidade**: score por confiança do OCR, distância do check‑in, frequência.
- **Chaves**: GTIN ou heurística de matching (nome normalizado + marca + unidade).
- **Privacidade**: `cpf_hash` com sal; dados pessoais segregados em `identity`.

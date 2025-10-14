# 🔄 Pipeline de Dados

1. **Captura cupom (NFC-e)** → validação → normalização → `pricing.price-observed`.
2. **Crawler panfletos** → OCR PaddleOCR/EasyOCR → parsing → score confiança.
3. **Código de barras** → input manual assistido → verificação por check‑in.
4. **Consolidação** (stream) → regras de peso + qualidade → materialização (Redis/PG).
5. **BI**: agregações batch/near‑real‑time, exportáveis e API para indústria.

## Qualidade de Dados
- Score de *matching* e *quality_score* por origem.
- Reprocessamentos assistidos; auditoria (data lineage leve).

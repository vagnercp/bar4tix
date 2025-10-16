SET search_path = catalog, public;
CREATE TABLE IF NOT EXISTS catalog.products (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  gtin VARCHAR(32) NOT NULL,
  name TEXT NOT NULL,
  brand TEXT,
  created_at timestamptz NOT NULL DEFAULT public.utcnow()
);
CREATE UNIQUE INDEX IF NOT EXISTS ux_products_gtin ON catalog.products(gtin);

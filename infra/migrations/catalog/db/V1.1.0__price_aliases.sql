SET search_path = catalog, public;
CREATE TABLE IF NOT EXISTS catalog.gtin_alias (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  gtin VARCHAR(32) NOT NULL,
  alias VARCHAR(64) NOT NULL,
  created_at timestamptz NOT NULL DEFAULT public.utcnow()
);
CREATE INDEX IF NOT EXISTS ix_gtin_alias_gtin ON catalog.gtin_alias(gtin);

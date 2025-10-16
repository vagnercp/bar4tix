SET search_path = evt, public;
CREATE TABLE IF NOT EXISTS evt.price_observations (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  store_id UUID NOT NULL,
  product_id UUID NOT NULL,
  amount NUMERIC(14,4) NOT NULL,
  currency CHAR(3) NOT NULL DEFAULT 'BRL',
  source TEXT NOT NULL,
  observed_at timestamptz NOT NULL DEFAULT public.utcnow()
);
CREATE INDEX IF NOT EXISTS ix_price_obs_store ON evt.price_observations(store_id);
CREATE INDEX IF NOT EXISTS ix_price_obs_product ON evt.price_observations(product_id);

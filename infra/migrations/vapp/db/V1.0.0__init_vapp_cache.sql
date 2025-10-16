SET search_path = vapp, public;
CREATE TABLE IF NOT EXISTS vapp.cache_materialized_prices (
  product_id UUID NOT NULL,
  store_id UUID NOT NULL,
  price NUMERIC(14,4) NOT NULL,
  updated_at timestamptz NOT NULL DEFAULT public.utcnow(),
  PRIMARY KEY (product_id, store_id)
);

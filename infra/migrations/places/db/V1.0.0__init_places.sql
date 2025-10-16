SET search_path = places, public;
CREATE TABLE IF NOT EXISTS places.stores (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name TEXT NOT NULL,
  google_place_id TEXT,
  lat DOUBLE PRECISION,
  lon DOUBLE PRECISION,
  created_at timestamptz NOT NULL DEFAULT public.utcnow()
);
CREATE INDEX IF NOT EXISTS ix_stores_geo ON places.stores(lat, lon);

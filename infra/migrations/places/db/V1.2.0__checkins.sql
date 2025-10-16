SET search_path = places, public;
CREATE TABLE IF NOT EXISTS places.checkins (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  store_id UUID NOT NULL REFERENCES places.stores(id),
  user_id UUID NOT NULL,
  checked_at timestamptz NOT NULL DEFAULT public.utcnow()
);
CREATE INDEX IF NOT EXISTS ix_checkins_store ON places.checkins(store_id);

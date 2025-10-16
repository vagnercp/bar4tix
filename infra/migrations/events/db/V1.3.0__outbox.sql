SET search_path = evt, public;
CREATE TABLE IF NOT EXISTS evt.outbox_events (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  aggregate_type TEXT NOT NULL,
  event_type TEXT NOT NULL,
  payload JSONB NOT NULL,
  occurred_at timestamptz NOT NULL,
  published BOOLEAN NOT NULL DEFAULT false,
  created_at timestamptz NOT NULL DEFAULT public.utcnow()
);
CREATE INDEX IF NOT EXISTS ix_outbox_unpublished ON evt.outbox_events(published) WHERE published = false;

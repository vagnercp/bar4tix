SET search_path = prof, public;
CREATE TABLE IF NOT EXISTS prof.preferences (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL,
  allow_marketing BOOLEAN NOT NULL DEFAULT false,
  created_at timestamptz NOT NULL DEFAULT public.utcnow()
);

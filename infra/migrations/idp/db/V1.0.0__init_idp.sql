SET search_path = idp, public;
CREATE TABLE IF NOT EXISTS idp.users (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  email TEXT UNIQUE,
  display_name TEXT,
  created_at timestamptz NOT NULL DEFAULT public.utcnow()
);

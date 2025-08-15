CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS members (
  uuid uuid PRIMARY KEY,
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now(),
  role varchar(20) NOT NULL DEFAULT 'USER',
  name varchar(30) NOT NULL,
  email varchar(100) NOT NULL UNIQUE,
  profile_image_url varchar(255),
  supabase_uuid uuid NOT NULL,
  auth_provider varchar(20) NOT NULL,
  is_cbt_approved boolean NOT NULL DEFAULT false
);

CREATE INDEX IF NOT EXISTS idx_member_email ON members(email);

CREATE TABLE IF NOT EXISTS member_agreement (
  member_uuid uuid NOT NULL,
  created_at timestamptz NOT NULL DEFAULT now(),
  is_agreed_over14_terms boolean NOT NULL DEFAULT false,
  over14_terms_agreed_at timestamptz NOT NULL DEFAULT now(),
  is_agreed_terms_of_service boolean NOT NULL DEFAULT false,
  terms_of_service_agreed_at timestamptz NOT NULL DEFAULT now(),
  is_agreed_privacy_policy boolean NOT NULL DEFAULT false,
  privacy_policy_agreed_at timestamptz NOT NULL DEFAULT now(),
  is_agreed_marketing_information boolean NOT NULL DEFAULT false,
  marketing_information_agreed_at timestamptz NOT NULL DEFAULT now(),
  PRIMARY KEY (member_uuid, created_at),
  CONSTRAINT fk_member_agreement_member FOREIGN KEY (member_uuid) REFERENCES members(uuid) ON DELETE CASCADE
);
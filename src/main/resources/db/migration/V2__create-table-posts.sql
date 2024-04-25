CREATE TABLE posts(
    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    title TEXT NOT NULL,
    description TEXT,
    created_by_id BIGINT REFERENCES users (id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

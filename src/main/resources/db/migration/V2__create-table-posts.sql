CREATE TABLE posts(
    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    active BOOLEAN default true,
    title TEXT NOT NULL,
    description TEXT,
    image_url VARCHAR(250),
    created_by_id BIGINT REFERENCES users (id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

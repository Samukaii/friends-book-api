CREATE TABLE users (
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    active BOOLEAN default true,
    name VARCHAR(250) NOT NULL,
    surname VARCHAR(250) NOT NULL,
    email TEXT NOT NULL UNIQUE,
    nickname TEXT NOT NULL UNIQUE,
    cover_url VARCHAR(250),
    profile_url VARCHAR(250),
    password TEXT NOT NULL
);

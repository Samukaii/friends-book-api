CREATE TABLE friends
(
    id        BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    user_id   INTEGER,
    friend_id INTEGER,
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_friends FOREIGN KEY (friend_id) REFERENCES users (id)
);

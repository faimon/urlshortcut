CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(2000) NOT NULL UNIQUE,
    password VARCHAR(2000) NOT NULL,
    site     VARCHAR(2000) NOT NULL UNIQUE
);

CREATE TABLE urls
(
    id          SERIAL PRIMARY KEY,
    url         TEXT NOT NULL,
    url_short   TEXT NOT NULL,
    count_calls INT,
    user_id     INT REFERENCES users (id)
);
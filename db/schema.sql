create table roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(2000) NOT NULL UNIQUE,
    password VARCHAR(2000) NOT NULL,
    site     VARCHAR(2000) NOT NULL UNIQUE,
    role_id  INT REFERENCES roles (id)
);

CREATE TABLE urls
(
    id          SERIAL PRIMARY KEY,
    url         TEXT NOT NULL UNIQUE,
    url_short   TEXT NOT NULL UNIQUE,
    count_calls INT,
    user_id     INT REFERENCES users (id)
);
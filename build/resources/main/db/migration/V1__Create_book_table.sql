create sequence hibernate_sequence start 1 increment 1;

CREATE TABLE IF NOT EXISTS book (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    availability BOOLEAN NOT NULL
    );
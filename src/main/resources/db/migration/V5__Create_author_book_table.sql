CREATE TABLE IF NOT EXISTS author_book(
    id BIGSERIAL PRIMARY KEY,
    book_id BIGINT,
    author_id BIGINT
    );

ALTER TABLE author_book
    ADD CONSTRAINT fkey_book FOREIGN KEY (book_id)
        REFERENCES book(id),
ADD CONSTRAINT fk_author FOREIGN KEY (author_id)
    REFERENCES author(id);
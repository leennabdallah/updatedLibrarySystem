CREATE TABLE IF NOT EXISTS borrowing_transactions(
    id BIGSERIAL PRIMARY KEY,
    borrow_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(255) NOT NULL,
    book_id BIGINT,
    borrower_id BIGINT
    );

ALTER TABLE borrowing_transactions
ADD CONSTRAINT fk_book FOREIGN KEY (book_id)
    REFERENCES book(id),
ADD CONSTRAINT fk_borrower FOREIGN KEY (borrower_id)
    REFERENCES borrowers(id);
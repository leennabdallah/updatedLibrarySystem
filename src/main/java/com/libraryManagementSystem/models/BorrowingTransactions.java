package com.libraryManagementSystem.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing_transactions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BorrowingTransactions {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BorrowingTransactions [id= ").append(id)
                .append(", borrow date= ").append(borrowDate)
                .append(", return date= ").append(returnDate)
                .append(", status= ").append(status.toString())
                .append(", book id= ").append(book.getId())
        .append("]");
        return builder.toString();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column( name = "borrow_date")
    private LocalDate borrowDate= LocalDate.now();

    @Column( name = "return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    @Column ( name = "status")
    private Status status;

    @ManyToOne
    @JoinColumn(name="book_id", referencedColumnName = "id")
    private Book book;

    @ManyToOne
    @JoinColumn(name= "borrower_id", referencedColumnName = "id")
    private Borrowers borrower;


}

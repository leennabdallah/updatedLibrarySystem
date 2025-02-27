package com.libraryManagementSystem.dto;
import com.libraryManagementSystem.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingTransactionsResponseDTO {
    private long id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Status status;
    private BookResponseDTO book;
    private BorrowersResponseDTO borrower;
}

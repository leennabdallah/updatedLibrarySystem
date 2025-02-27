package com.libraryManagementSystem.dto;
import com.libraryManagementSystem.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.libraryManagementSystem.models.Status.BORROW;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowingTransactionsRequestDTO {
    private long id;
    private Status status;
    private long bookId;
    private long borrowerId;
}

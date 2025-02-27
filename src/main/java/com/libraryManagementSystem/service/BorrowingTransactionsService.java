package com.libraryManagementSystem.service;

import com.libraryManagementSystem.models.Book;
import com.libraryManagementSystem.models.Borrowers;
import com.libraryManagementSystem.models.BorrowingTransactions;
import com.libraryManagementSystem.models.Status;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

public interface BorrowingTransactionsService {

    BorrowingTransactions createBorrowingTransaction(Long borrowerId, Long bookId, Status status);

    List<Book> allBooksBorrowedByUser(Long borrowerId);

    List<Borrowers> allBorrowersWhoBorrowedBook(Long bookId);
}

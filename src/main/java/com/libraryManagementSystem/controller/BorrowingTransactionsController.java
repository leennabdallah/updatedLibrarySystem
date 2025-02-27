package com.libraryManagementSystem.controller;
import com.libraryManagementSystem.dto.*;
import com.libraryManagementSystem.mapper.Mapper;
import com.libraryManagementSystem.models.*;
import com.libraryManagementSystem.service.AuthorBookService;
import com.libraryManagementSystem.service.BorrowingTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/borrowing-transactions")

public class BorrowingTransactionsController {
    @Autowired
    private Mapper mapper;

    @Autowired
    private BorrowingTransactionsService borrowingTransactionsService;


    @PostMapping("/create")
    public BorrowingTransactionsResponseDTO createBorrowingTransactions(@RequestBody BorrowingTransactionsRequestDTO requestDTO) {
        return mapper.convertTransactionToResponse( borrowingTransactionsService
                .createBorrowingTransaction(requestDTO.getBorrowerId(),
                        requestDTO.getBookId(), requestDTO.getStatus()));
    }

    @GetMapping("/all-books-borrowed-by-user/{userId}")
    public List<BookResponseDTO> getAllBorrowedBooksByUserId(@PathVariable Long userId) {
        List<Book> books= borrowingTransactionsService.allBooksBorrowedByUser(userId);
        List<BookResponseDTO> bookResponseDTOs = new ArrayList<>();
        for (Book book : books) {
            bookResponseDTOs.add(mapper.convertBookToResponseDTO(book));
        }
        return bookResponseDTOs;
    }

    @GetMapping("/all-borrowers-of-book/{bookId}")
    public List<BorrowersResponseDTO> getAllBorrowersOfBook(@PathVariable Long bookId) {
        List<Borrowers> allBorrowers= borrowingTransactionsService.allBorrowersWhoBorrowedBook(bookId);
        List<BorrowersResponseDTO> borrowersResponseDTOs = new ArrayList<>();
        for (Borrowers borrower : allBorrowers) {
            borrowersResponseDTOs.add(mapper.convertBorrowerToResponseDTO(borrower));
        }
        return borrowersResponseDTOs;
    }

}

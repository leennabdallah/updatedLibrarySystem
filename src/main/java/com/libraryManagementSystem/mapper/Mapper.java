package com.libraryManagementSystem.mapper;

import com.libraryManagementSystem.dto.*;
import com.libraryManagementSystem.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    @Autowired
    private ModelMapper modelMapper;

    public AuthorBook convertRequestToEntity(AuthorBookRequestDTO authorBookRequestDTO) {
        return modelMapper.map(authorBookRequestDTO, AuthorBook.class);
    }

    public AuthorBookResponseDTO convertEntityToResponse(AuthorBook authorBook) {
        return modelMapper.map(authorBook, AuthorBookResponseDTO.class);
    }
    public Author convertReqToAuthor(AuthorRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Author.class);
    }

    public AuthorResponseDTO convertAuthorToResponseDTO(Author author) {
        return modelMapper.map(author, AuthorResponseDTO.class);
    }

    public Book convertReqToBook(BookRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Book.class);
    }

    public BookResponseDTO convertBookToResponseDTO(Book book) {
        return modelMapper.map(book, BookResponseDTO.class);
    }

    public Borrowers convertReqToBorrower(BorrowersRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Borrowers.class);
    }
    public BorrowersResponseDTO convertBorrowerToResponseDTO(Borrowers borrower) {
        return modelMapper.map(borrower, BorrowersResponseDTO.class);
    }


    public BorrowingTransactionsResponseDTO convertTransactionToResponse(BorrowingTransactions borrowingTransactions) {
        return modelMapper.map(borrowingTransactions, BorrowingTransactionsResponseDTO.class);
    }
}

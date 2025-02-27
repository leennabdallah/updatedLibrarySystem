package com.libraryManagementSystem.service.impl;

import com.libraryManagementSystem.feign.EmailClient;
import com.libraryManagementSystem.models.Book;
import com.libraryManagementSystem.models.Borrowers;
import com.libraryManagementSystem.models.BorrowingTransactions;
import com.libraryManagementSystem.models.Status;
import com.libraryManagementSystem.repositories.BookRepository;
import com.libraryManagementSystem.repositories.BorrowersRepository;
import com.libraryManagementSystem.repositories.BorrowingTransactionsRepository;
import com.libraryManagementSystem.service.BorrowingTransactionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.libraryManagementSystem.models.Status.RETURN;

@Slf4j
@Service
public class BorrowingTransactionsServiceImpl implements BorrowingTransactionsService {
    @Autowired
    private BorrowingTransactionsRepository borrowingTransactionsRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowersRepository borrowersRepository;

    @Autowired
    private EmailClient emailClient;

    @Value("${borrowers.transaction.limit}")
    private int maxTransactions;
    private BorrowingTransactions saveTransaction(Borrowers borrower, Book book, Status status, LocalDate date) {
        BorrowingTransactions borrowingTransaction = new BorrowingTransactions();
        borrowingTransaction.setBook(book);
        borrowingTransaction.setBorrower(borrower);
        borrowingTransaction.setStatus(status);
        borrowingTransaction.setReturnDate(date);
        return borrowingTransactionsRepository.save(borrowingTransaction);
    }

    @Override
    public BorrowingTransactions createBorrowingTransaction(Long borrowerId, Long bookId, Status status) {

        log.info("creating borrowingTransaction");
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Borrowers> borrower = borrowersRepository.findById(borrowerId);
        if (book.isEmpty())
        {
            log.error("book not found");
            throw new EntityNotFoundException("book not found");
        }
        if (borrower.isEmpty()){
            log.error("borrower not found");
            throw new EntityNotFoundException("borrower not found");
        }
        if (status.equals(Status.BORROW))
        {
            return borrowBook(book.get(), borrower.get());
        }
        else if (status.equals(Status.RETURN))
        {
            return returnBook(book.get(), borrower.get());
        }
        else{
            log.error("invalid status");
            throw new EntityNotFoundException("invalid status");
        }
    }

    private BorrowingTransactions borrowBook(Book book, Borrowers borrower) {
        String to= borrower.getEmail();
        String subject= "borrowing book: " + book.getTitle();
        log.info("Borrower: {} borrowBook is attempting to borrow: {}", borrower.getId(), book.getId());
        if (borrower.getTransactions().size()>= maxTransactions){

            log.error("max transactions reached");
            throw new RuntimeException("max transactions reached");
        }
        if (book.isAvailability()){
            book.setAvailability(false);
            bookRepository.save(book);
            BorrowingTransactions transaction= saveTransaction(borrower, book, Status.BORROW, null);
            log.info("Borrower: {} borrowed book: {}", borrower.getId(), book.getId());
            String body= "Hi "+ borrower.getName()+ "! "+ book.getTitle()+" was borrowed successfully on: "+LocalDate.now();
            emailClient.sendEmail(to, subject, body);
            return transaction;
        }
        else{
            log.error("book is not available");
            String body= "Hi "+ borrower.getName()+ "! Unfortunately, "+ book.getTitle()+" is currently not available.";
            emailClient.sendEmail(to, subject, body);
            return null;
        }
    }

    private BorrowingTransactions returnBook( Book book, Borrowers borrower) {
        String to= borrower.getEmail();
        String subject= "returning book: " + book.getTitle();
        log.info("Borrower: {} is attempting to return book: {}", borrower.getId(), book.getId());
        if (book.isAvailability()){
            log.error("book is already returned");
            return null;
        }
        BorrowingTransactions transaction= borrowingTransactionsRepository.findByBorrowerIdAndBookId(borrower.getId(), book.getId());
        if (transaction == null){
            log.error("User: {} did not borrow the book: {}", borrower.getId(), book.getId());
            throw new EntityNotFoundException("User did not borrow this book");
        }

        String body= "Hi "+ borrower.getName()+ "! "+ book.getTitle()+" was returned successfully on: "+LocalDate.now();
        emailClient.sendEmail(to, subject, body);
        transaction.setStatus(RETURN);
        transaction.setReturnDate(LocalDate.now());
        book.setAvailability(true);
        bookRepository.save(book);
        return borrowingTransactionsRepository.save(transaction);
    }

    @Override
    public List<Book> allBooksBorrowedByUser(Long borrowerId){
        List<BorrowingTransactions> transactions = borrowingTransactionsRepository.findByBorrowerId(borrowerId);
        List<Book> books = new ArrayList<>();
        for (BorrowingTransactions transaction : transactions){
            books.add(transaction.getBook());
        }
        return books;
    }

    @Override
    public List<Borrowers> allBorrowersWhoBorrowedBook(Long bookId){
        List<BorrowingTransactions> transactions=  borrowingTransactionsRepository.findByBookId(bookId);
        List<Borrowers> borrowers = new ArrayList<>();
        for (BorrowingTransactions transaction : transactions){
            borrowers.add(transaction.getBorrower());
        }
        return borrowers;
    }



}

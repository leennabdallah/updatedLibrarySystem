package com.libraryManagementSystem.service.impl;

import com.libraryManagementSystem.service.AuthorBookService;
import lombok.extern.slf4j.Slf4j;
import com.libraryManagementSystem.models.Author;
import com.libraryManagementSystem.models.AuthorBook;
import com.libraryManagementSystem.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libraryManagementSystem.repositories.AuthorBookRepository;
import com.libraryManagementSystem.repositories.AuthorRepository;
import com.libraryManagementSystem.repositories.BookRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Slf4j
@Service
public class AuthorBookServiceImpl implements AuthorBookService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorBookRepository authorBookRepository;



    @Override
    public AuthorBook createAuthorBook(long authorId, long bookId) {
        log.info("Creating AuthorBook: author id: {}, book id: {}", authorId, bookId);
        Optional<Author> author= authorRepository.findById(authorId);
        Optional<Book> book= bookRepository.findById(bookId);
        if(book.isEmpty() || author.isEmpty()) {
            log.error("Error creating author book");
            throw new EntityNotFoundException("Error creating author book");
        }
        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthor(author.get());
        authorBook.setBook(book.get());
        return authorBookRepository.save(authorBook);
    }

    @Override
    public AuthorBook addBookToAuthor(Long authorId, Long bookId) {
        log.info("entering method addBookToAuthor.");
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<Book> book = bookRepository.findById(bookId);
        if (author.isEmpty() || book.isEmpty()) {
            log.error("Author or book not found");
            throw new EntityNotFoundException("Author or Book not found");
        }
        AuthorBook authorBook= createAuthorBook(authorId, bookId);
        log.info("adding book {} to author {}", bookId, authorId);
        log.info("Exiting method addBookToAuthor");
        return authorBook;
    }

    @Override
    public List<Book> getAllBooksByAuthor(Long authorId) {
        log.info("entering method getAllBooksByAuthorId");
        List<AuthorBook> authorBooks = authorBookRepository.findByAuthorId(authorId);
        if (authorBooks.isEmpty()) {
            log.error("No books written by the author");
            throw new EntityNotFoundException("No books written by the author");
        }
        List<Book> booksByAuthor= new ArrayList<>();
        for (AuthorBook authorBook : authorBooks) {
            Book book = authorBook.getBook();
            log.info("Book found with id: {} is a book written by author: {}", book.getId(), authorId);
            booksByAuthor.add(book);
        }
        log.info("Exiting method getAllBooksByAuthorId");
        return booksByAuthor;
    }

    @Override
    public Set<Author> getAllAuthorsOfBook(Long bookId) {
        log.info("entering method getAllAuthorsByBookId");
        List<AuthorBook> authorBooks = authorBookRepository.findByBookId(bookId);
        if (authorBooks.isEmpty()) {
            log.error("No authors assigned for this book");
            throw new EntityNotFoundException("No authors assigned for this book");
        }
        Set<Author> authorsOfBook= new HashSet<>();
        for (AuthorBook authorBook : authorBooks) {
            log.info("Author of id: {} wrote book {}", authorBook.getAuthor().getId(), bookId);
            authorsOfBook.add(authorBook.getAuthor());
        }
        log.info("Exiting method getAllAuthorsByBookId");
        return authorsOfBook;
    }

    @Override
    public List<AuthorBook> getAllAuthorBook(){
        log.info("entering method getAllAuthorBook");
        List<AuthorBook> authorBooks = authorBookRepository.findAll();
        if (authorBooks.isEmpty()) {
            log.error("No authors assigned for any books");
            throw new EntityNotFoundException("No authors assigned for any books");
        }
        log.info("Exiting method getAllAuthorBook");
        return authorBooks;
    }

    @Override
    public String deleteAuthorBook(Long authorBookId) {
        log.info("entering method deleteAuthorBook");
        if (!authorBookRepository.existsById(authorBookId)) {
            log.error("AuthorBook entity not found");
            throw new EntityNotFoundException("AuthorBook entity not found");
        }
        authorBookRepository.deleteById(authorBookId);
        log.info("Exiting method deleteAuthorBook");
        return "AuthorBook with id: "+authorBookId+ " deleted successfully";
    }






}

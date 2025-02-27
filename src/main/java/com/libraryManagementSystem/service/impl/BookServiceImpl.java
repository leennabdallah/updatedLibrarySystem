package com.libraryManagementSystem.service.impl;


import com.libraryManagementSystem.models.Author;
import com.libraryManagementSystem.models.AuthorBook;
import com.libraryManagementSystem.repositories.AuthorBookRepository;
import com.libraryManagementSystem.repositories.AuthorRepository;
import com.libraryManagementSystem.service.BookService;
import lombok.extern.slf4j.Slf4j;
import com.libraryManagementSystem.models.Book;
import com.libraryManagementSystem.models.Category;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.libraryManagementSystem.repositories.BookRepository;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorBookRepository authorBookRepository;

    @Override
    public Book createBook(Book book) throws IOException, ParseException {
        log.info("Creating new book: {}", book.toString());
        String ISBN2 = book.getIsbn();
        RestTemplate restTemplate = new RestTemplate();
        String format="json";
        String jscmd="data";
        String baseUrl = "https://openlibrary.org/api/books";
        log.info("baseUrl: {} created.", baseUrl);
        StringBuilder isbnBuilder= new StringBuilder();
        isbnBuilder.append("ISBN:").append(ISBN2);
        String isbn= isbnBuilder.toString();
        String url=baseUrl+"?bibkeys="+isbn+"&format="+format+"&jscmd="+ jscmd;
        log.info("String Url: {} created.", url);

        ResponseEntity<Object> response = restTemplate.getForEntity(url , Object.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new EntityNotFoundException("Book not found");
        }
        log.info("Book created: {}", book.toString());
        String title= (String)(((LinkedHashMap)((LinkedHashMap)response.getBody()).get(isbn)).get("title"));
        log.info("Title found: {}", title);
        book.setTitle(title);
        book= bookRepository.save(book);
        ArrayList listeDeAuthors= (ArrayList)(((LinkedHashMap)((LinkedHashMap)response.getBody()).get(isbn)).get("authors"));
        for (int i=0;i<listeDeAuthors.size();i++) {
            String nom= (String)(((LinkedHashMap)(listeDeAuthors.get(i))).get("name"));
            Author author= new Author();
            if (authorRepository.findByName(nom)==null) {
                author.setName(nom);
                log.info("Author created: {}", author.toString());
                author= authorRepository.save(author);
            }
            else{
                log.info("Author found: {}", author.toString());
                author= authorRepository.findByName(nom);

            }
            AuthorBook authorBook= new AuthorBook();
            authorBook.setAuthor(author);
            authorBook.setBook(book);
            log.info("AuthorBook created: {}", authorBook.toString());
            authorBookRepository.save(authorBook);
        }
        return book;

    }

    @Override
    public Book viewBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            log.error("Book with id: {} not found", id);
            throw new EntityNotFoundException("Book not found");
        }
        return book.get();
    }

    @Override
    public Book setBookUnavailable(long id) {
        if (bookRepository.findById(id).isEmpty()) {
            log.error("Book with id {} is not found", id );
            throw new EntityNotFoundException("Book with id " + id + " is not found");
        }
        bookRepository.setBookUnavailable(id);
        //bookRepository.save(bookRepository.findById(id).get());
        return bookRepository.findById(id).get();
    }

    @Override
    public Book setBookAvailable(long id) {
        if (bookRepository.findById(id).isEmpty()) {
            log.error("Book with id {} is not found", id );
            throw new EntityNotFoundException("Book with id " + id + " is not found");
        }
        bookRepository.setBookAvailable(id);
//        bookRepository.save(bookRepository.findById(id).get());
        log.info("Book with id {} found and its availability updated to true", id);
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBooks() {
        log.info("Getting all books");
        if (bookRepository.findAll().isEmpty()) {
            log.error("No books found");
            throw new EntityNotFoundException("No books found");
        }
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(long id) {
        log.info("Getting book with id {}", id);
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            log.error("Book with id {} is not found", id);
            throw new EntityNotFoundException("Book with id " + id + " is not found");
        }
        log.info("Book with id {} found", id);
        return book.get();
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        log.info("Getting book with title {}", title);
        List<Book> books= bookRepository.findByTitleContainingIgnoreCase(title);
        if (books.isEmpty()) {
            log.error("No books found");
            throw new EntityNotFoundException("No books found");
        }
        log.info("Books containing title {} found", title);
        return books;
    }

    @Override
    public List<Book> findBookByCategory(Category category) {
        List<Book> books= bookRepository.findByCategory(category);
        if (books.isEmpty()) {
            log.error("No books found in the category: {}", category);
        }
        log.info("Books containing category {} found", category);
        return books;
    }

    @Override
    public String deleteBookByID(long id) {
        log.info("Deleting book with id {}", id);
        if (bookRepository.findById(id).isEmpty()) {
            log.error("Book with id {} is not found", id);
            throw new EntityNotFoundException("Book with id " + id + " is not found");
        }
        authorBookRepository.deleteAll(authorBookRepository.findByBookId(id));
        bookRepository.deleteById(id);
        return "Book with id " + id + " deleted";
    }

}

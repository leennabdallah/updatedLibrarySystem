package com.libraryManagementSystem.service;

import com.libraryManagementSystem.models.Book;
import com.libraryManagementSystem.models.Category;
import org.apache.tomcat.util.json.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface BookService {
    Book createBook(Book book) throws IOException, ParseException;

    Book viewBook(Long id);

    Book setBookUnavailable(long id);

    Book setBookAvailable(long id);

    List<Book> getAllBooks();

    Book findBookById(long id);

    List<Book> findBookByTitle(String title);

    List<Book> findBookByCategory(Category category);

    String deleteBookByID(long id);
}

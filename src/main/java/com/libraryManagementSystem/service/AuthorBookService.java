package com.libraryManagementSystem.service;

import com.libraryManagementSystem.models.Author;
import com.libraryManagementSystem.models.AuthorBook;
import com.libraryManagementSystem.models.Book;

import java.util.List;
import java.util.Set;

public interface AuthorBookService {

    AuthorBook createAuthorBook(long authorId, long bookId);

    AuthorBook addBookToAuthor(Long authorId, Long bookId);

    List<Book> getAllBooksByAuthor(Long authorId);

    Set<Author> getAllAuthorsOfBook(Long bookId);

    List<AuthorBook> getAllAuthorBook();

    String deleteAuthorBook(Long authorBookId);
}

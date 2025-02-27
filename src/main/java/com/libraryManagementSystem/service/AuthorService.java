package com.libraryManagementSystem.service;

import com.libraryManagementSystem.models.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);

    List<Author> getAllAuthors();

    Author findAuthorById(long id);

    Author updateAuthorBiography(long id, String biography);

    String deleteAuthor(long id);
}

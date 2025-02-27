package com.libraryManagementSystem.repositories;

import com.libraryManagementSystem.models.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Long> {
    /*@Query("SELECT ab FROM AuthorBook ab WHERE ab.author.id = :authorId")
    List<AuthorBook> findByAuthorId(@Param("authorId") long authorId);

    @Query("SELECT ab FROM AuthorBook ab WHERE ab.book.id = :bookId")
    Set<AuthorBook> findByBookId(@Param("bookId") long bookId);*/

    List<AuthorBook> findByAuthorId(Long authorId);
    List<AuthorBook> findByBookId(Long bookId);
    List<AuthorBook> findByAuthorIdAndBookId(Long authorId, Long bookId);


}

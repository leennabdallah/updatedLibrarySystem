package com.libraryManagementSystem.repositories;
import com.libraryManagementSystem.models.Book;
import com.libraryManagementSystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{
    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.availability = false WHERE b.id = :bookId")
    void setBookUnavailable(@Param("bookId") long bookId);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByCategory(Category category);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.availability = true WHERE b.id = :bookId")
    void setBookAvailable(@Param("bookId") long bookId);

}

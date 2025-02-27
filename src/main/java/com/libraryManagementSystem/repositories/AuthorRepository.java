package com.libraryManagementSystem.repositories;

import com.libraryManagementSystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

}

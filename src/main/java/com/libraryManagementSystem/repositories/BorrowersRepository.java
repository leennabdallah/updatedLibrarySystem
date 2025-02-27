package com.libraryManagementSystem.repositories;
import com.libraryManagementSystem.models.Borrowers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowersRepository extends JpaRepository<Borrowers, Long>{
}

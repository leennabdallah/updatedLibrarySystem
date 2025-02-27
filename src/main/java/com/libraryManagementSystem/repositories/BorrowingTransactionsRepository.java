package com.libraryManagementSystem.repositories;
import com.libraryManagementSystem.models.BorrowingTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BorrowingTransactionsRepository extends JpaRepository<BorrowingTransactions, Long>{

    @Transactional
    @Query("SELECT tr FROM BorrowingTransactions tr WHERE tr.borrower.id= :borrowerId AND tr.book.id = :bookId AND tr.status= 'BORROW'")
    public BorrowingTransactions findByBorrowerIdAndBookId(@Param("borrowerId")Long borrowerId, @Param("bookId") Long bookId);

    @Transactional
    @Query("SELECT tr FROM BorrowingTransactions tr WHERE tr.borrower.id= :borrowerId")
    public List<BorrowingTransactions> findByBorrowerId(@Param("borrowerId")Long borrowerId);

    @Transactional
    @Query("SELECT tr FROM BorrowingTransactions tr WHERE tr.book.id= :bookId")
    public List<BorrowingTransactions> findByBookId(@Param("bookId")Long bookId);
}

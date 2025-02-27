package com.libraryManagementSystem.service;

import com.libraryManagementSystem.models.Borrowers;

import java.util.List;

public interface BorrowersService {
    Borrowers createBorrower(Borrowers borrower);

    List<Borrowers> getAllBorrowers();

    Borrowers findBorrowersById(long id);

    Borrowers updateBorrowerEmail(long id, String email);

    Borrowers updateBorrowerPhone(long id, String phoneNumber);

    String deleteBorrower(long id);
}

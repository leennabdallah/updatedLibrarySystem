package com.libraryManagementSystem.service.impl;

import com.libraryManagementSystem.feign.EmailClient;
import com.libraryManagementSystem.service.BorrowersService;
import lombok.extern.slf4j.Slf4j;
import com.libraryManagementSystem.models.Borrowers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libraryManagementSystem.repositories.BorrowersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BorrowersServiceImpl implements BorrowersService {
    @Autowired
    private BorrowersRepository borrowersRepository;

    @Autowired
    private EmailClient emailClient;
    @Override
    public Borrowers createBorrower(Borrowers borrower) {
        log.info("Creating new borrower");
        String to= borrower.getEmail();
        String subject= "Successful registration";
        String body= "Welcome to library Management System, "+ borrower.getName()+ "!";
        emailClient.sendEmail(to,subject,body);
        return borrowersRepository.save(borrower);
    }

    @Override
    public List<Borrowers> getAllBorrowers() {
        log.info("Getting all borrowers");
        if (borrowersRepository.findAll().isEmpty()) {
            log.error("No borrowers found");
            throw new EntityNotFoundException("No borrowers found");
        }
        return borrowersRepository.findAll();
    }

    @Override
    public Borrowers findBorrowersById(long id) {
        log.info("Getting borrower with id {}", id);
        Optional<Borrowers> borrower = borrowersRepository.findById(id);
        if (borrower.isEmpty()) {
            log.error("Borrower with id {} is not found", id);
            throw new EntityNotFoundException("Borrower with id " + id + " is not found");
        }
        log.info("Borrower with id {} found", id);
        return borrower.get();
    }

    @Override
    public Borrowers updateBorrowerEmail(long id, String email) {
        log.info("Updating borrower email with id {}", id);
        Optional<Borrowers> borrower = borrowersRepository.findById(id);
        if (borrower.isEmpty()) {
            log.error("Borrower with id {} not found", id);
        }
        borrower.get().setEmail(email);
        return borrowersRepository.save(borrower.get());
    }

    @Override
    public Borrowers updateBorrowerPhone(long id, String phoneNumber) {
        log.info("Updating borrower email with id {}", id);
        Optional<Borrowers> borrower = borrowersRepository.findById(id);
        if (borrower.isEmpty()) {
            log.error("Borrower with id {} not found", id);
        }
        borrower.get().setPhoneNumber(phoneNumber);
        return borrowersRepository.save(borrower.get());
    }

    @Override
    public String deleteBorrower(long id) {
        log.info("Deleting borrower with id {}", id);
        Optional<Borrowers> borrower = borrowersRepository.findById(id);
        if (borrower.isEmpty()) {
            log.error("Borrower with id {} not found", id);
            throw new EntityNotFoundException("Borrower with id " + id + " not found");
        }
        borrowersRepository.delete(borrower.get());
        log.info("Borrower with id {} deleted", id);
        return "Borrower with id " + id + " deleted";
    }

}

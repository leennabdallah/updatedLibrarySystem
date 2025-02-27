package com.libraryManagementSystem.controller;

import com.libraryManagementSystem.dto.BorrowersRequestDTO;
import com.libraryManagementSystem.dto.BorrowersResponseDTO;
import com.libraryManagementSystem.mapper.Mapper;
import com.libraryManagementSystem.models.Borrowers;
import com.libraryManagementSystem.service.BorrowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/borrowers")

public class BorrowersController {
    @Autowired
    private BorrowersService borrowersService;

    @Autowired
    private Mapper mapper;


    @PostMapping("/create")
    public BorrowersResponseDTO createBorrower(@RequestBody BorrowersRequestDTO requestDTO) {
        Borrowers borrower = mapper.convertReqToBorrower(requestDTO);
        borrowersService.createBorrower(borrower);
        return mapper.convertBorrowerToResponseDTO(borrower);
    }

    @GetMapping("/{borrowerId}")
    public BorrowersResponseDTO getBorrower(@PathVariable long borrowerId) {
        Borrowers borrower= borrowersService.findBorrowersById(borrowerId);
        return mapper.convertBorrowerToResponseDTO(borrower);
    }

    @GetMapping("/all")
    public List<BorrowersResponseDTO> getAllBorrowers(){
        List<Borrowers> borrowers = borrowersService.getAllBorrowers();
        List<BorrowersResponseDTO> borrowersResponseDTOs = new ArrayList<>();
        for (Borrowers borrower : borrowers) {
            borrowersResponseDTOs.add(mapper.convertBorrowerToResponseDTO(borrower));
        }
        return borrowersResponseDTOs;
    }

    @PatchMapping("/change-email/{borrowerId}")
    public BorrowersResponseDTO changeEmail(@PathVariable long borrowerId, @RequestBody String email) {
        Borrowers borrower = borrowersService.updateBorrowerEmail(borrowerId, email);
        return mapper.convertBorrowerToResponseDTO(borrower);
    }

    @PatchMapping("/change-phone-number/{borrowerId}")
    public BorrowersResponseDTO changePhoneNumber(@PathVariable long borrowerId, @RequestBody String phoneNumber) {
        Borrowers borrower= borrowersService.updateBorrowerPhone(borrowerId, phoneNumber);
        return mapper.convertBorrowerToResponseDTO(borrower);
    }

    @DeleteMapping("/delete/{borrowerId}")
    public String deleteBorrower(@PathVariable long borrowerId) {
        return borrowersService.deleteBorrower(borrowerId);
    }
}

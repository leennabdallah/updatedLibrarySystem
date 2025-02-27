package com.libraryManagementSystem.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowersResponseDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String email;
}

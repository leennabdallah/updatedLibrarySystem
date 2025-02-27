package com.libraryManagementSystem.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowersRequestDTO {
    private long id;
    private String name;
    @Pattern(regexp="^[A-Za-z0-9]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message= "invalid email format")
    private String email;
    private String phoneNumber;
}

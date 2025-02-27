package com.libraryManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorBookResponseDTO {
    private long id;
    private AuthorResponseDTO author;
    private BookResponseDTO book;
}

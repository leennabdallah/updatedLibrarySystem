package com.libraryManagementSystem.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthorBookRequestDTO {
    private long id;
    private long bookId;
    private long authorId;

}

package com.libraryManagementSystem.dto;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorResponseDTO {
    private long id;
    private String name;
    private String biography;
}

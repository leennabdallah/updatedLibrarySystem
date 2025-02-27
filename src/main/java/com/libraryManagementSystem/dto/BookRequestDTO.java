package com.libraryManagementSystem.dto;
import com.libraryManagementSystem.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookRequestDTO {
    private long id;
    private String title;
    private String isbn;
    private Category category;
}

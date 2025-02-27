package com.libraryManagementSystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Book [bookId= ").append(id).append(", book title= ")
                .append(title).append(", isbn= ").append(isbn).append(", availability= ")
                .append(availability).append(", category= ").append(category.toString()).append("]");

        return builder.toString();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "title cannot be null")
    @Column(name = "title")
    private String title;

    @NotNull(message = "isbn cannot be null")
    @Column(name = "isbn", unique = true)
    private String isbn;

    @NotNull(message = "availability cannot be null")
    @Column(name = "availability")
    private boolean availability=true;

    @NotNull(message = "category cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;


    @OneToMany
    @JoinColumn(name="book_id", referencedColumnName = "id")
    private Set<BorrowingTransactions> transactions= new HashSet<>();
}

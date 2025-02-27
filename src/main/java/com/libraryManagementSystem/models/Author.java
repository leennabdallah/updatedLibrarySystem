package com.libraryManagementSystem.models;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Author {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Author [id= ").append(id).append(", name= ").append(name).append(", biography= ").append(biography)
                .append(", age= ").append(age).append("]");
        return builder.toString();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name= "biography")
    private String biography;

    @Column(name= "age")
    private int age;

}

package com.libraryManagementSystem.controller;

import com.libraryManagementSystem.dto.AuthorRequestDTO;
import com.libraryManagementSystem.dto.AuthorResponseDTO;
import com.libraryManagementSystem.mapper.Mapper;
import com.libraryManagementSystem.models.Author;
import com.libraryManagementSystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")

public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private Mapper mapper;

    @PostMapping("/create")
    public AuthorResponseDTO createAuthor(@RequestBody AuthorRequestDTO requestDTO) {
        Author author = mapper.convertReqToAuthor(requestDTO);
        return mapper.convertAuthorToResponseDTO(authorService.createAuthor(author));
    }

    @GetMapping("/view/{id}")
    public AuthorResponseDTO viewAuthor(@PathVariable long id) {
        return mapper.convertAuthorToResponseDTO(authorService.findAuthorById(id));
    }

    @GetMapping("/get-all-authors")
    public List<AuthorResponseDTO> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        List<AuthorResponseDTO> authorResponseDTOs = new ArrayList<>();
        for (Author author : authors) {
            authorResponseDTOs.add(mapper.convertAuthorToResponseDTO(author));
        }
        return authorResponseDTOs;
    }

    @PatchMapping("/update/{id}")
    public AuthorResponseDTO updateAuthorBiography(@PathVariable long id, @RequestBody String biography) {
        return mapper.convertAuthorToResponseDTO(authorService.updateAuthorBiography(id, biography));
    }

    @DeleteMapping("/by-id/{id}")
    public String deleteAuthor(@PathVariable long id) {
        return authorService.deleteAuthor(id);
    }

}

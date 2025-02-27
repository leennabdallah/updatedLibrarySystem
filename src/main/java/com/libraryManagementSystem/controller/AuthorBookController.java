package com.libraryManagementSystem.controller;

import com.libraryManagementSystem.dto.AuthorBookRequestDTO;
import com.libraryManagementSystem.dto.AuthorBookResponseDTO;
import com.libraryManagementSystem.dto.AuthorResponseDTO;
import com.libraryManagementSystem.dto.BookResponseDTO;
import com.libraryManagementSystem.mapper.Mapper;
import com.libraryManagementSystem.models.*;
import com.libraryManagementSystem.service.AuthorBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/author-book")

public class AuthorBookController {
    @Autowired
    private AuthorBookService authorBookService;

    @Autowired
    private Mapper mapper;

    @PostMapping("/add-author-book")
    public AuthorBookResponseDTO addAuthorBook(@RequestBody AuthorBookRequestDTO authorBookRequestDTO) {
        long authorId= authorBookRequestDTO.getAuthorId();
        long bookId= authorBookRequestDTO.getBookId();
        return mapper.convertEntityToResponse(authorBookService
                .createAuthorBook(authorId, bookId));
    }

    @PostMapping("/add-book-to-author/{authorId}/{bookId}")
    public AuthorBookResponseDTO addBookToAuthor(@PathVariable long authorId, @PathVariable long bookId) {
        return mapper.convertEntityToResponse(authorBookService.addBookToAuthor(authorId, bookId));
    }

    @PostMapping("/add-author-to-Book/{bookId}/{authorId}")
    public AuthorBookResponseDTO addAuthorToBook(@PathVariable long bookId, @PathVariable long authorId) {
        return mapper.convertEntityToResponse(authorBookService.addBookToAuthor(authorId, bookId));
    }

    @GetMapping("/get-all-books-written-by/{authorId}")
    public List<BookResponseDTO> getAllBooksByAuthor(@PathVariable long authorId) {
        List<Book> booksByAuthor= authorBookService.getAllBooksByAuthor(authorId);
        List<BookResponseDTO> bookResponseDTOSet= new ArrayList<>();
        for (Book book : booksByAuthor) {
            bookResponseDTOSet.add(mapper.convertBookToResponseDTO(book));
        }
        return bookResponseDTOSet;
    }

    @GetMapping("/get-all-authors-of/{bookId}")
    public Set<AuthorResponseDTO> getAllAuthorsOfBook(@PathVariable long bookId) {
        Set<Author> authors= authorBookService.getAllAuthorsOfBook(bookId);
        Set<AuthorResponseDTO> authorResponseDTOSet= new HashSet<>();
        for (Author author : authors) {
            authorResponseDTOSet.add(mapper.convertAuthorToResponseDTO(author));
        }
        return authorResponseDTOSet;
    }

    @GetMapping("/all")
    public List<AuthorBookResponseDTO> getAllAuthorBooks(){
        List<AuthorBook> all= authorBookService.getAllAuthorBook();
        List<AuthorBookResponseDTO> authorBookResponseDTOList= new ArrayList<>();
        for (AuthorBook authorBook : all) {
            authorBookResponseDTOList.add(mapper.convertEntityToResponse(authorBook));
        }
        return authorBookResponseDTOList;
    }

    @DeleteMapping("/{authorBookId}")
    public String deleteAuthorBookById(@PathVariable long authorBookId) {
        return authorBookService.deleteAuthorBook(authorBookId);
    }

}

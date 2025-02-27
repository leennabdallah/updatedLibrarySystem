package com.libraryManagementSystem.controller;

import com.libraryManagementSystem.dto.BookRequestDTO;
import com.libraryManagementSystem.dto.BookResponseDTO;
import com.libraryManagementSystem.mapper.Mapper;
import com.libraryManagementSystem.models.Book;
import com.libraryManagementSystem.models.Category;
import com.libraryManagementSystem.service.BookService;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private Mapper mapper;

    @PostMapping("/create")
    public BookResponseDTO createBook(@RequestBody BookRequestDTO requestDTO) throws IOException, ParseException {

        return mapper.convertBookToResponseDTO(bookService.createBook(mapper.convertReqToBook(requestDTO)));
    }

    @GetMapping("/view/{id}")
    public BookResponseDTO viewBook(@PathVariable long id) {
        Book book = bookService.viewBook(id);
        return mapper.convertBookToResponseDTO(book);
    }
    @PatchMapping("/set-book-unavailable/{id}")
    public BookResponseDTO setBookUnavailable(@PathVariable long id) {
        Book book = bookService.setBookUnavailable(id);
        return mapper.convertBookToResponseDTO(book);
    }

    @PatchMapping("/set-book-available/{id}")
    public BookResponseDTO setBookAvailable(@PathVariable long id) {
        Book book = bookService.setBookAvailable(id);
        return mapper.convertBookToResponseDTO(book);
    }
    @GetMapping("/get-all-books")
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookResponseDTO> bookResponseDTOs = new ArrayList<>();
        for(Book book : books) {
            bookResponseDTOs.add(mapper.convertBookToResponseDTO(book));
        }
        return bookResponseDTOs;
    }

    @GetMapping("/find-by-id/{id}")
    public BookResponseDTO findBookById(@PathVariable long id) {
        Book book = bookService.findBookById(id);
        return mapper.convertBookToResponseDTO(book);
    }

    @GetMapping("/find-by-title/{title}")
    public List<BookResponseDTO> findBookByTitle(@PathVariable String title) {
        List<Book> books = bookService.findBookByTitle(title);
        List<BookResponseDTO> bookResponseDTOs = new ArrayList<>();
        for(Book book : books) {
            bookResponseDTOs.add(mapper.convertBookToResponseDTO(book));
        }
        return bookResponseDTOs;
    }

    @GetMapping("/find-by-category/{category}")
    public List<BookResponseDTO> findBookByCategory(@PathVariable Category category) {
        List<Book> books = bookService.findBookByCategory(category);
        List<BookResponseDTO> bookResponseDTOs = new ArrayList<>();
        for(Book book : books) {
            bookResponseDTOs.add(mapper.convertBookToResponseDTO(book));
        }
        return bookResponseDTOs;
    }

    @DeleteMapping("/by-id/{id}")
    public String deleteBook(@PathVariable long id) {
        return bookService.deleteBookByID(id);
    }



}

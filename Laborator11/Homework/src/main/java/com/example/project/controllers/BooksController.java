package com.example.project.controllers;

import com.example.project.entities.Book;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.repositories.BookRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Api(value = "Books", tags = {"Books"})
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(value = "Get all books")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @ApiOperation(value = "Create a new book")
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @ApiOperation(value = "Update a book by ID")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthorId(bookDetails.getAuthorId());
        book.setGenres(bookDetails.getGenres());
        book.setPublicationDate(bookDetails.getPublicationDate());
        book.setLanguage(bookDetails.getLanguage());
        book.setPublishingHouse(bookDetails.getPublishingHouse());

        return bookRepository.save(book);
    }

    @ApiOperation(value = "Delete a book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}

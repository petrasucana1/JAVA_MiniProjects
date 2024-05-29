package com.example.project.controllers;

import com.example.project.entities.Book;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.repositories.BookRepository;
import com.example.project.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing books.
 */
@RestController
@RequestMapping("/api/books")
@Api(value = "Books", tags = {"Books"})
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    /**
     * Get all books.
     *
     * @return a list of all books
     */
    @ApiOperation(value = "Get all books")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Create a new book.
     *
     * @param book the book to create
     * @return the created book
     */
    @ApiOperation(value = "Create a new book")
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    /**
     * Update a book by ID.
     *
     * @param id the ID of the book to update
     * @param bookDetails the details to update
     * @return the updated book
     * @throws ResourceNotFoundException if the book is not found
     */
    @ApiOperation(value = "Update a book by ID")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthorId(bookDetails.getAuthorId());
        book.setGenres(bookDetails.getGenres());
        book.setPublicationDate(bookDetails.getPublicationDate());
        book.setLanguage(bookDetails.getLanguage());
        book.setPublishingHouse(bookDetails.getPublishingHouse());

        return bookRepository.save(book);
    }

    /**
     * Delete a book by ID.
     *
     * @param id the ID of the book to delete
     * @return a response entity indicating the result of the operation
     * @throws ResourceNotFoundException if the book is not found
     */
    @ApiOperation(value = "Delete a book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }

    /**
     * Get the longest reading sequence.
     *
     * @return a list of books in the recommended reading order
     */
    @GetMapping("/longest-sequence")
    public List<Book> getLongestReadingSequence() {
        return bookService.getRecommendedReadingOrder();
    }
}

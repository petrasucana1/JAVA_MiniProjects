package com.example.project.controllers;

import com.example.project.client.AuthorClient;
import com.example.project.client.BookClient;
import com.example.project.entities.Author;
import com.example.project.entities.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@Api(value = "Client Controller", tags = {"Client Controller"})
public class ClientController {

    @Autowired
    private AuthorClient authorClient;

    @Autowired
    private BookClient bookClient;

    @ApiOperation(value = "Get all authors")
    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorClient.getAllAuthors();
    }

    @ApiOperation(value = "Get all books")
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookClient.getAllBooks();
    }

    @ApiOperation(value = "Get a book by ID")
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookClient.getBookById(id);
    }

    @ApiOperation(value = "Create a new book")
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookClient.createBook(book);
    }
}

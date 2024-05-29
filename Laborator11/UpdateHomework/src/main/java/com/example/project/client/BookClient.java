package com.example.project.client;

import com.example.project.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class BookClient {

    private static final String BASE_URL = "http://localhost:8081/api/books";

    @Autowired
    private RestTemplate restTemplate;

    public List<Book> getAllBooks() {
        return Arrays.asList(restTemplate.getForObject(BASE_URL, Book[].class));
    }

    public Book getBookById(int id) {
        return restTemplate.getForObject(BASE_URL + "/" + id, Book.class);
    }

    public Book createBook(Book book) {
        return restTemplate.postForObject(BASE_URL, book, Book.class);
    }

    public void updateBook(int id, Book book) {
        restTemplate.put(BASE_URL + "/" + id, book);
    }

    public void deleteBook(int id) {
        restTemplate.delete(BASE_URL + "/" + id);
    }
}

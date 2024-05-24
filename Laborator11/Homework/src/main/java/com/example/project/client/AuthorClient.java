package com.example.project.client;

import com.example.project.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AuthorClient {

    private static final String BASE_URL = "http://localhost:8081/api/authors";

    @Autowired
    private RestTemplate restTemplate;

    public List<Author> getAllAuthors() {
        return Arrays.asList(restTemplate.getForObject(BASE_URL, Author[].class));
    }
}

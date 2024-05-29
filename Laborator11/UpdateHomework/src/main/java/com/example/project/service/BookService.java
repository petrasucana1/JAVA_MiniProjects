package com.example.project.service;

import com.example.project.entities.Book;
import com.example.project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getRecommendedReadingOrder() {
        List<Book> allBooks = bookRepository.findAll();

        allBooks.sort(Comparator.comparing(Book::getPublicationMonth)
                .thenComparing(Book::getPublicationDay));

        return findLongestSequence(allBooks);
    }

    private List<Book> findLongestSequence(List<Book> books) {
        List<Book> longestSequence = new ArrayList<>();
        List<Book> currentSequence = new ArrayList<>();

        for (int i = 0; i < books.size() - 1; i++) {
            if (books.get(i).getPublicationDate().getYear() < books.get(i + 1).getPublicationDate().getYear()) {
                currentSequence.add(books.get(i));
            } else {
                if (currentSequence.size() > longestSequence.size()) {
                    longestSequence = new ArrayList<>(currentSequence);
                }
                currentSequence.clear();
            }
        }

        if (currentSequence.size() > longestSequence.size()) {
            longestSequence = new ArrayList<>(currentSequence);
        }

        return longestSequence;
    }

}


package org.example.Models;

import org.example.Graph.BookNode;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ReadingList extends AbstractModel{
    Timestamp CreatedAt;
    Set<BookNode> books;

    public ReadingList()
    {
        this.books=new HashSet<>();
        this.CreatedAt = Timestamp.from(Instant.now());
    }
    public ReadingList(Set<BookNode> books)
    {
        this.books=books;
        this.CreatedAt = Timestamp.from(Instant.now());
    }
    public void addBook(BookNode book) {
        books.add(book);
    }
    public Timestamp getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        CreatedAt = createdAt;
    }

    public Set<BookNode> getBooks() {
        return books;
    }

    public void setBooks(Set<BookNode> books) {
        this.books = books;
    }
}

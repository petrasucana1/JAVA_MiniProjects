package org.example.repository;

import org.example.entities.Book;

import javax.persistence.Query;
import java.util.List;

public class BookRepository extends AbstractRepository<Book, Integer> {

    public BookRepository(){
        super();
    }
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    protected String getFindByNameQueryName() {
        return "Book.findByName";
    }

}

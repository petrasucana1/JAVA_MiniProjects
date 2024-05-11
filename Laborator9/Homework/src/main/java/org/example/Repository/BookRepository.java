package org.example.Repository;

import org.example.Entities.Book;

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

package org.example.repository;

import org.example.entities.Author;

import javax.persistence.Query;
import java.util.List;

public class AuthorRepository extends AbstractRepository<Author, Integer> {

    public AuthorRepository(){
        super();
    }
    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }

    @Override
    protected String getFindByNameQueryName() {
        return "Author.findByName";
    }
}

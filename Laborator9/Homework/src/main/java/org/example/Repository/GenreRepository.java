package org.example.Repository;

import org.example.Entities.Genre;

import javax.persistence.Query;
import java.util.List;

public class GenreRepository extends AbstractRepository<Genre, Integer> {

    public GenreRepository(){
        super();
    }
    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }

    @Override
    protected String getFindByNameQueryName() {
        return "Genre.findByName";
    }
}

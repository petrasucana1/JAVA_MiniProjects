package org.example.Repository;

import org.example.Entities.Book;
import org.example.Entities.Genre;
import org.example.Entities.PublishingHouse;

import javax.persistence.Query;
import java.util.List;

public class PublishingHouseRepository extends AbstractRepository<PublishingHouse, Integer> {

    public PublishingHouseRepository(){
        super();
    }
    @Override
    protected Class<PublishingHouse> getEntityClass() {
        return PublishingHouse.class;
    }

    @Override
    protected String getFindByNameQueryName() {
        return "PublishingHouse.findByName";
    }

    public void addBook(Integer id, Book book){
     //  entityManager.getTransaction().commit();
        PublishingHouse publishingHouse=findById(id);
        if(publishingHouse!=null){
            publishingHouse.getBooks().add(book);
            book.setPublishingHouse(publishingHouse);
        }

    }
}
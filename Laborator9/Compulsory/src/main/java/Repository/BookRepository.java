package org.example.Repository;

import org.example.Entities.Book;
import org.example.Entities.ManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class BookRepository {

    private final EntityManager entityManager = ManagerFactory.getEntityManagerFactory().createEntityManager();

    public void create(Book book) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(book);
        transaction.commit();
    }

    public Book findById(int id) {
        return entityManager.find(Book.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Book> findByName(String namePattern) {
        Query query = entityManager.createNamedQuery("Book.findByName");
        query.setParameter("name", "%" + namePattern + "%");
        return query.getResultList();
    }

    public void closeEntityManager() {
        entityManager.close();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}

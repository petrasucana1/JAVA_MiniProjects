package org.example.Repository;

import org.example.Entities.Author;
import org.example.Entities.ManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class AuthorRepository {

    private final EntityManager entityManager = ManagerFactory.getEntityManagerFactory().createEntityManager();

    public void create(Author author) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(author);
        transaction.commit();
    }

    public Author findById(int id) {
        return entityManager.find(Author.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Author> findByName(String namePattern) {
        Query query = entityManager.createQuery("SELECT a FROM Author a WHERE a.name LIKE :name");
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

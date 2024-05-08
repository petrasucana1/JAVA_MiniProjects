package org.example.Repository;

import org.example.Entities.Genre;
import org.example.Entities.ManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class GenreRepository {

    private final EntityManager entityManager = ManagerFactory.getEntityManagerFactory().createEntityManager();

    public void create(Genre genre) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(genre);
        transaction.commit();
    }

    public Genre findById(int id) {
        return entityManager.find(Genre.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Genre> findByName(String namePattern) {
        Query query = entityManager.createQuery("SELECT g FROM Genre g WHERE g.name LIKE :name");
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

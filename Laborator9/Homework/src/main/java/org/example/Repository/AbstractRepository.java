package org.example.repository;

import org.example.entities.ManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.example.LoggerConfig;


public abstract class AbstractRepository<T, ID extends Serializable> {

    protected EntityManager entityManager;

    public AbstractRepository() {
        this.entityManager = ManagerFactory.getEntityManagerFactory().createEntityManager();
    }

    public void create(T entity) {
        long startTime = System.currentTimeMillis();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            LoggerConfig.getLogger().log(Level.INFO, "Entity created successfully: {0}", entity.toString());
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            LoggerConfig.getLogger().log(Level.INFO, "CREATE executed in: " + executionTime + " milliseconds");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerConfig.getLogger().log(Level.SEVERE, "Failed to create entity: " + e.getMessage(), e);
        }
    }

    public T findById(ID id) {
        try {
            return entityManager.find(getEntityClass(), id);
        } catch (Exception e) {
            LoggerConfig.getLogger().log(Level.SEVERE, "Failed to find entity by id: " + e.getMessage(), e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> findByName(String namePattern) {
        long startTime = System.currentTimeMillis();
        try {
            Query query = entityManager.createNamedQuery(getFindByNameQueryName());
            query.setParameter("name", "%" + namePattern + "%");
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            LoggerConfig.getLogger().log(Level.INFO, "FIND_BY_NAME executed in: " + executionTime + " milliseconds");
            return query.getResultList();
        } catch (Exception e) {
            LoggerConfig.getLogger().log(Level.SEVERE, "Failed to execute find by name query: " + e.getMessage(), e);
            return null;
        }
    }

    public void delete(ID id) {
        long startTime = System.currentTimeMillis();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T entity = entityManager.find(getEntityClass(), id);
            if (entity != null) {
                entityManager.remove(entity);
                transaction.commit();
                LoggerConfig.getLogger().log(Level.INFO, "Entity deleted successfully: {0}", entity.toString());
            } else {
                LoggerConfig.getLogger().log(Level.WARNING, "Entity with id " + id + " not found for deletion");
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            LoggerConfig.getLogger().log(Level.INFO, "DELETE executed in: " + executionTime + " milliseconds");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerConfig.getLogger().log(Level.SEVERE, "Failed to delete entity: " + e.getMessage(), e);
        }
    }

    public void update(T entity) {
        long startTime = System.currentTimeMillis();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            LoggerConfig.getLogger().log(Level.INFO, "Entity updated successfully: {0}", entity.toString());
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            LoggerConfig.getLogger().log(Level.INFO, "UPDATE executed in: " + executionTime + " milliseconds");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LoggerConfig.getLogger().log(Level.SEVERE, "Failed to update entity: " + e.getMessage(), e);
        }
    }

    public void closeEntityManager() {
        entityManager.close();
        LoggerConfig.getLogger().log(Level.INFO, "Entity manager closed");
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    protected abstract Class<T> getEntityClass();

    protected abstract String getFindByNameQueryName();
}

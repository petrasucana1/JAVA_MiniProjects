package org.example.Entities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerFactory{

    private static final String PERSISTENCE_UNIT_NAME = "MyApplicationPU";
    private static EntityManagerFactory entityManagerFactory;

    private ManagerFactory() {}

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}

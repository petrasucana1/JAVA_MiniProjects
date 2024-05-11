package org.example.Repository;

import org.example.Entities.Book;
import org.example.Entities.PublishingHouse;
import org.example.LoggerConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class PublishingHouseRepositoryTest {


    private EntityManager entityManager;
    private PublishingHouseRepository publishingHouseRepository;


    @Before
    public void setUp() {
        publishingHouseRepository = new PublishingHouseRepository();
        entityManager=publishingHouseRepository.getEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }


    @Test

    public void create() {
        LoggerConfig.getLogger().info("Test started");

        PublishingHouse publishingHouse= new PublishingHouse("Arthur");
        BookRepository bookRepository = new BookRepository();
        Book book = new Book("Book :))", 1, LocalDate.now(), "English", null);

        entityManager.getTransaction().begin();
        entityManager.persist(publishingHouse);
        entityManager.persist(book);

        publishingHouseRepository.addBook(publishingHouse.getId(),book);
        entityManager.persist(book);
        entityManager.getTransaction().commit();


        PublishingHouse retrievedPH = entityManager.find(PublishingHouse.class, publishingHouse.getId());
        assertNotNull(retrievedPH);
        assertEquals("Arthur", retrievedPH.getName());
    }

    @Test
    public void findById() {
        LoggerConfig.getLogger().info("Test started");

        PublishingHouse publishingHouse= new PublishingHouse("TREI");
        publishingHouseRepository.create(publishingHouse);

        BookRepository bookRepository = new BookRepository();
        Book book= new Book("Test1 Book", 1, LocalDate.now(), "English",null);
        bookRepository.create(book);

        publishingHouseRepository.addBook(publishingHouse.getId(),book);


        PublishingHouse foundPublishingHouse = publishingHouseRepository.findById(publishingHouse.getId());

        assertNotNull(foundPublishingHouse);

        assertEquals("TREI", foundPublishingHouse.getName());
    }

    @Test
    public void findByName() {
        LoggerConfig.getLogger().info("Test started");

        PublishingHouse publishingHouse1= new PublishingHouse("Litera");
        PublishingHouse publishingHouse2= new PublishingHouse("Delphin");

        entityManager.getTransaction().begin();
        entityManager.persist(publishingHouse1);
        entityManager.persist(publishingHouse2);
        entityManager.getTransaction().commit();

        List<PublishingHouse> publishingHouses = publishingHouseRepository.findByName("Litera");

        assertNotNull(publishingHouses);
    }
}
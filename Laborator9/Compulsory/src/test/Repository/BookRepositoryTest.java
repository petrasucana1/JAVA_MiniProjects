package org.example.Repository;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import org.example.Entities.Book;
import org.example.Repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BookRepositoryTest {

    private EntityManager entityManager;
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository = new BookRepository();
        entityManager=bookRepository.getEntityManager();
    }

    @After
    public void tearDown() {
        entityManager.close();
    }

    @Test
    public void create() {
        Book book = new Book("Test Book", 1, 1, LocalDate.now(), "English");
        bookRepository.create(book);

        Book retrievedBook = entityManager.find(Book.class, book.getId());

        assertNotNull(retrievedBook);

        assertEquals("Test Book", retrievedBook.getTitle());
    }

    @Test
    public void findById() {

        Book book = new Book("Test Book", 1, 1, LocalDate.now(), "English");
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();


        Book foundBook = bookRepository.findById(book.getId());


        assertNotNull(foundBook);

        assertEquals("Test Book", foundBook.getTitle());
    }

    @Test
    public void findByName() {
        Book book1 = new Book("Test Book 1", 1, 1, LocalDate.now(), "English");
        Book book2 = new Book("Test Book 2", 1, 1, LocalDate.now(), "English");
        entityManager.getTransaction().begin();
        entityManager.persist(book1);
        entityManager.persist(book2);
        entityManager.getTransaction().commit();

        List<Book> books = bookRepository.findByName("Test");

        assertNotNull(books);
        assertEquals(2, books.size());
    }
}


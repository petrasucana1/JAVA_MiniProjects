package org.example.entities;

import org.example.LoggerConfig;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

@Entity
@Table(name = "genres")
@NamedQuery(
        name = "Genre.findByName",
        query = "SELECT g FROM Genre g WHERE g.name LIKE :name"
)
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Book> books = new HashSet<>();

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    // Getters and setters

    public void addBook(Book book){
        LoggerConfig.getLogger().info("Entity method invoked");

        boolean added=books.add(book);
        if(added){
            book.getGenres().add(this);
        }
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }



    // toString method
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

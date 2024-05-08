package org.example.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String title;

    @Column(name="author_id")
    private Integer authorId;

    @Column(name="genre_id")
    private Integer genreId;

    @Column(name="publication_date")
    private LocalDate publicationDate;

    @Column(name="language")
    private String language;

    public Book() {}

    public Book(String title, Integer authorId, Integer genreId, LocalDate publicationDate, String language) {
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
        this.publicationDate = publicationDate;
        this.language = language;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    // toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                ", publicationDate=" + publicationDate +
                ", language='" + language + '\'' +
                '}';
    }
}

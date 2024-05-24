package com.example.project.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@NamedQuery(
        name = "Book.findByName",
        query = "SELECT b FROM Book b WHERE b.title LIKE :name"
)
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String title;

    @Column(name="author_id")
    private Integer authorId;

    @ManyToMany(cascade = {
            CascadeType.PERSIST ,
            CascadeType.MERGE

    })
    @JoinTable(
            name = "book_genre",
            joinColumns ={ @JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")},
            uniqueConstraints={@UniqueConstraint( columnNames = {"book_id", "genre_id"}
            )})
    private Set<Genre> genres = new HashSet<>();

    @Column(name="publication_date")
    private LocalDate publicationDate;

    @Column(name="language")
    private String language;

    @Column(name = "publishing_house_id")
    private Integer publishingHouseId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publishing_house_id", insertable = false, updatable = false)
    private PublishingHouse publishingHouse;


    public Book() {}

    public Book(String title, Integer authorId, LocalDate publicationDate, String language, PublishingHouse publishingHouse) {
        this.title = title;
        this.authorId = authorId;
        this.publicationDate = publicationDate;
        this.language = language;
        this.publishingHouse = publishingHouse;
    }

    // Getters and setters

    public void addGenre(Genre genre){

        boolean added= genres.add(genre);
        if(added){
            genre.getBooks().add(this);
        }
    }

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

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
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

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
        this.publishingHouseId = publishingHouse != null ? publishingHouse.getId() : null;
    }


    // toString method

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", publicationDate=" + publicationDate +
                ", language='" + language + '\'' +
                ", publishingHouse=" + publishingHouse +
                '}';
    }
}

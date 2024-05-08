package org.example.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "genres")
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    public Genre() {}

    public Genre(String name) {
        this.name = name;
    }

    // Getters and setters
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

    // toString method
    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

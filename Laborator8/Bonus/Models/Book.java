package org.example.Models;

import java.time.LocalDate;
import java.util.List;

    public class Book extends AbstractModel {
        private int authorId;
        private int genreId;
        private LocalDate publicationDate;
        private String language;

        public Book(String title, int authorId, int genreId, LocalDate publicationDate, String language) {
            this.setName(title);
            this.authorId = authorId;
            this.genreId = genreId;
            this.publicationDate = publicationDate;
            this.language = language;
        }
        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }

        public int getGenreId() {
            return genreId;
        }

        public void setGenreId(int genreId) {
            this.genreId = genreId;
        }

        public LocalDate getPublicationDate() {
            return publicationDate;
        }

        public void setPublicationDate(LocalDate publicationDate) {
            this.publicationDate = publicationDate;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "authorId=" + authorId +
                    ", genreId=" + genreId +
                    ", publicationDate=" + publicationDate +
                    ", language='" + language + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }



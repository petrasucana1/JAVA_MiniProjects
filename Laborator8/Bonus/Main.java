package org.example;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import org.example.DAOClasses.*;
import org.example.Graph.BookNode;
import org.example.Graph.BooksGraph;
import org.example.Models.*;
import org.example.Database;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection con;
        try {
            String csvDBPath="C:\\Users\\petra\\Desktop\\FII\\ANULII\\SEM2\\JAVA\\Lab8\\Compulsory\\src\\main\\resources\\BooksDB.csv";
            Reader reader = new FileReader(csvDBPath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());


            con = Database.getConnection();
            con.setAutoCommit(false);

            var authorDAO = new AuthorDAO();
            var genreDAO = new GenreDAO();
            var bookDAO = new BookDAO();

            BooksGraph booksGraph = new BooksGraph();

            csvParser.iterator().forEachRemaining(record -> {
                try {
                    String authorName = record.get("authors");
                    String genreName = record.get("genre");

                    Author author = authorDAO.findByName(authorName, con);
                    if (author == null) {
                        author = new Author(authorName);
                        authorDAO.create(author, con);
                    }

                    Genre genre = genreDAO.findByName(genreName, con);
                    if (genre == null) {
                        genre = new Genre(genreName);
                        genreDAO.create(genre, con);
                    }

                    String title = record.get("title");
                    LocalDate publicationDate = LocalDate.parse(record.get("publicationDate"));
                    String language = record.get("language");

                    Book book = bookDAO.findByName(title, con);
                    if (book == null) {
                        book = new Book(title, authorDAO.findByName(authorName, con).getId(),
                                genreDAO.findByName(genreName, con).getId(), publicationDate, language);
                        bookDAO.create(book, con);
                    }
                } catch (SQLException e) {
                    System.err.println("Error while performing database operations: " + e.getMessage());
                }
            });

            csvParser.close();
            reader.close();

            List<Book> allBooks = bookDAO.findAll(con);
            for (Book book : allBooks){
                BookNode bookNode = new BookNode(book);
                booksGraph.addVertex(bookNode);

            }

            booksGraph.equitableColoring();
       //   booksGraph.printBooksByColor();
       //   booksGraph.partitionBooksIntoReadingLists();
            booksGraph.partitionBooks();
            booksGraph.printReadingLists();

            Database.closeConnection(con);
        } catch (SQLException | IOException | NullPointerException e) {
            System.err.println("Error while performing database operations: " + e.getMessage());
        }
    }

}




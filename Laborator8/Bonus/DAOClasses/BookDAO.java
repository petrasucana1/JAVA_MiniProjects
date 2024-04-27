package org.example.DAOClasses;

import org.example.Database;
import org.example.Models.Author;
import org.example.Models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAO extends GenericDAO<Book> {
    @Override
    protected String getTableName() {
        return "books";
    }

    @Override
    protected Book createModelFromResultSet(ResultSet rs) throws SQLException {
        int Id=rs.getInt("id");
        String title = rs.getString("name");
        int authorId = rs.getInt("author_id");
        int genreId = rs.getInt("genre_id");
        java.sql.Date publicationDate = rs.getDate("publication_date");
        String language = rs.getString("language");

        Book newB= new Book(title, authorId, genreId, publicationDate.toLocalDate(), language);
        newB.setId(Id);
        return newB;

    }

    @Override
    public void create(Book book, Connection con) throws SQLException {
        String sql = "INSERT INTO books (name, author_id, genre_id, publication_date, language) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setInt(2, book.getAuthorId());
            pstmt.setInt(3, book.getGenreId());
            pstmt.setDate(4, java.sql.Date.valueOf(book.getPublicationDate()));
            pstmt.setString(5, book.getLanguage());
            pstmt.executeUpdate();
            con.commit();
            System.out.println("Book: " + book.getName() + " added successfully in books table\n");
        } catch (SQLException e) {
            System.out.println("Error while creating book: " + e.getMessage());
        }
    }
}

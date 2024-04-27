package org.example.DAOClasses;

import org.example.Database;
import org.example.Models.Author;
import org.example.Models.Book;
import org.example.Models.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDAO extends GenericDAO<Genre> {
    @Override
    protected String getTableName() {
        return "genres";
    }

    @Override
    protected Genre createModelFromResultSet(ResultSet rs) throws SQLException {
        int Id=rs.getInt("id");
        String genreName = rs.getString("name");

        Genre newG= new Genre(genreName);
        newG.setId(Id);
        return newG;
    }


    @Override
    public void create(Genre genre, Connection con) throws SQLException {
        String sql = "INSERT INTO genres (name) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, genre.getName());
            pstmt.executeUpdate();
            con.commit();
            System.out.println("Genre: " + genre.getName() + " added successfully in genres table\n");
        } catch (SQLException e) {
            System.out.println("Error while creating genre: " + e.getMessage());
        }
    }

}

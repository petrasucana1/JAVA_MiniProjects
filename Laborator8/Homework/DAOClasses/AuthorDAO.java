package org.example.DAOClasses;

import org.example.Database;
import org.example.Models.Author;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO extends GenericDAO<Author> {
    @Override
    protected String getTableName() {
        return "authors";
    }

    @Override
    protected Author createModelFromResultSet(ResultSet rs) throws SQLException {
        int Id=rs.getInt("id");
        String authorName = rs.getString("name");
        Author newA= new Author(authorName);
        newA.setId(Id);
        return newA;
    }


    @Override
    public void create(Author author, Connection con) throws SQLException {
        String sql = "INSERT INTO authors (name) VALUES (?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, author.getName());
            pstmt.executeUpdate();
            con.commit();
            System.out.println("Author: " + author.getName() + " added successfully in authors table\n");
        } catch (SQLException e) {
            System.out.println("Error while creating author: " + e.getMessage());
        }
    }
}

package org.example;
import org.example.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO authors (author_name) VALUES (?)")) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException e) {
        System.out.println("Error while creating author: " + e.getMessage());
    }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT author_id FROM authors WHERE author_name = ?")) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getInt("author_id") : null;
            }
        } catch (SQLException e) {
            System.out.println("Error while finding author by name: " + e.getMessage());
            return null;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT author_name FROM authors WHERE author_id = ?")) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString("author_name") : null;
            }
        } catch (SQLException e) {
            System.out.println("Error while finding author by id: " + e.getMessage());
            return null;
        }
    }

    public void deleteById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "DELETE FROM authors WHERE author_id = ?")) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Author with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Author with ID " + id + " not found.");
            }
            con.commit();
        }
    }

}

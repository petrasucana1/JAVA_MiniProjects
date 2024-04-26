package org.example.DAOClasses;

import org.example.Database;
import org.example.Models.AbstractModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T extends AbstractModel> {
    protected abstract String getTableName();
    protected abstract T createModelFromResultSet(ResultSet rs) throws SQLException;

    public abstract void create(T model, Connection con) throws SQLException ;

    public List<T> findAll(Connection con) throws SQLException {
        List<T> models = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                models.add(createModelFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error while finding models: " + e.getMessage());
        }
        return models;
    }

    public T findById(int id, Connection con) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return createModelFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while finding model by ID: " + e.getMessage());
        }
        return null;
    }

    public void deleteById(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Model with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Model with ID " + id + " not found.");
            }
            con.commit();
        } catch (SQLException e) {
            System.out.println("Error while deleting model by ID: " + e.getMessage());
        }
    }

    public T findByName(String name, Connection con) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE name = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return createModelFromResultSet(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error while finding model by name: " + e.getMessage());
        }
        return null;
    }
}

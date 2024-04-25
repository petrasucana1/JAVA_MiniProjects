package org.example;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        AuthorDAO authorDAO = new AuthorDAO();

        // Adaugare autor
        try {
            authorDAO.create("Camila Ore");
            System.out.println("Author Camila Ore added successfully.");
        } catch (SQLException e) {
            System.out.println("Error while adding author: " + e.getMessage());
        }

        // Căutare autor după nume
        try {
            Integer authorId = authorDAO.findByName("Camila Ore");
            if (authorId != null) {
                System.out.println("Author found with ID: " + authorId);
            } else {
                System.out.println("Author not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while searching for author by name: " + e.getMessage());
        }

        // Căutare autor după ID
        try {
            String authorName = authorDAO.findById(10);
            if (authorName != null) {
                System.out.println("Author found with name: " + authorName);
            } else {
                System.out.println("Author not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error while searching for author by ID: " + e.getMessage());
        }

        //Stergere autor dupa Id
        try {
            authorDAO.deleteById(9);
        } catch (SQLException e) {
            System.out.println("Error while deleting author by ID: " + e.getMessage());
        }

        // Închidere conexiune la baza de date
        Database.closeConnection();
    }
}

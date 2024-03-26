package company;

import company.model.*;
import company.repository.*;
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {


        String masterDirectoryPath = "C:/Users/petra/Desktop/FII/ANULII/SEM2/JAVA/Lab5/Compulsory/src/MasterDirectory";

        try {
            Repository repository = new Repository(masterDirectoryPath);

            System.out.println("Master Directory with the path: " + masterDirectoryPath + " has the following sub-directories:");

            repository.displayRepositoryContent();
        } catch (InvalidRepositoryException e) {
            System.err.println("Error: " + e.getMessage());

        }

    }
}
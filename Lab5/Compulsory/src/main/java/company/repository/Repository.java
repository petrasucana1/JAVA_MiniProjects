package company.repository;
import company.model.Document;
import company.model.Person;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

public class Repository {
    private String masterDirectoryPath;

    public Repository(String directory) throws InvalidRepositoryException {
        if (!isValidDirectory(directory)) {
            throw new InvalidRepositoryException("Invalid or non-existent master directory: " + directory);
        }
        this.masterDirectoryPath = directory;
    }

    private boolean isValidDirectory(String directory) {
        File file = new File(directory);
        return file.exists() && file.isDirectory();
    }


    public void displayRepositoryContent() {
        File masterDirectory = new File(masterDirectoryPath);
        File[] personDirectories = masterDirectory.listFiles(File::isDirectory);

        if (personDirectories != null) {
            for (File personDirectory : personDirectories) {
                System.out.println("Person directory: " + personDirectory.getName());
                File[] files = personDirectory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        System.out.println("  - " + file.getName());
                    }
                }
            }
        }
    }
}



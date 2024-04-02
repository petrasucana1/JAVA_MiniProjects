package company.repository;

import company.model.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public List<File> getAllSubdirectories() {
        File masterDirectory= new File(masterDirectoryPath);
        List<File> subdirectories = new ArrayList<>();
        if (masterDirectory.exists() && masterDirectory.isDirectory()) {
            File[] subdirectoryFiles = masterDirectory.listFiles(File::isDirectory);
            if (subdirectoryFiles != null) {
                for (File subdirectoryFile : subdirectoryFiles) {
                    subdirectories.add(subdirectoryFile);
                }
            }
        }
        return subdirectories;
    }

    public List<Document> getAllDocuments() {
        File masterDirectory= new File(masterDirectoryPath);
        List<Document> documents = new ArrayList<>();
        if (masterDirectory.exists() && masterDirectory.isDirectory()) {
            File[] subdirectoryFiles = masterDirectory.listFiles(File::isDirectory);
            if (subdirectoryFiles != null) {
                for (File subdirectoryFile : subdirectoryFiles) {
                    File[] documentFiles = subdirectoryFile.listFiles(File::isFile);
                    if (documentFiles != null) {
                        for (File documentFile : documentFiles) {
                            documents.add(new Document(documentFile.getName()));
                        }
                    }
                }
            }
        }
        return documents;
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



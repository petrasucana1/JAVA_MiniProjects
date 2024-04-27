package org.example.Graph;

import org.example.Models.Book;
import org.example.Models.ReadingList;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.*;

public class BooksGraph {
    private Graph<BookNode, DefaultEdge> graph;
    private int colors;

    private List<ReadingList> readingLists;

    public BooksGraph() {
        graph = new SimpleGraph<>(DefaultEdge.class);
        colors = 0;
        readingLists=new ArrayList<>();
    }

    public void addVertex(BookNode book) {
        try {
            graph.addVertex(book);
            updateEdges(book);
        } catch (Exception e) {
            System.err.println("Error while adding vertex to graph: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateEdges(BookNode book1) {
        try {
            for (BookNode book2 : graph.vertexSet()) {
                if (book1 != book2 &&(book1.getBook().getAuthorId() == book2.getBook().getAuthorId() || book1.getBook().getGenreId() == book2.getBook().getGenreId())) {
                    graph.addEdge(book1, book2);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while updating edges: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void equitableColoring() {
        // Map to store the color assigned to each vertex
        Map<BookNode, Integer> colorMap = new HashMap<>();

        // List to store the vertices sorted by degree in descending order
        List<BookNode> sortedVertices = new ArrayList<>(graph.vertexSet());
        sortedVertices.sort(Comparator.comparingInt(graph::degreeOf).reversed());

        // Greedy coloring
        for (BookNode vertex : sortedVertices) {
            Set<Integer> neighborColors = new HashSet<>();
            for (BookNode neighbor : Graphs.neighborListOf(graph, vertex)) {
                if (colorMap.containsKey(neighbor)) {
                    neighborColors.add(colorMap.get(neighbor));
                }
            }
            int color = 0;
            while (neighborColors.contains(color)) {
                color++;
            }
            colorMap.put(vertex, color);
            colors = Math.max(colors, color + 1);
        }

        // Assign colors to vertices
        for (Map.Entry<BookNode, Integer> entry : colorMap.entrySet()) {
            entry.getKey().setColor(entry.getValue());
        }

        System.out.println(colors);


    }
    public void printAllNodes() {
        System.out.println("All Nodes:");

        for (BookNode node : graph.vertexSet()) {
            System.out.println("- " + node );
        }
    }

   /* public void partitionBooksIntoReadingLists() {
        Set<BookNode> unassignedBooks = new HashSet<>(graph.vertexSet());

        while (!unassignedBooks.isEmpty()) {
            Set<BookNode> currentReadingList = new HashSet<>();
            Iterator<BookNode> iterator = unassignedBooks.iterator();
            BookNode currentBook = iterator.next();
             iterator.remove();
            currentReadingList.add(currentBook);

            // Parcurgem lista de liste de lectură existente pentru a verifica dacă cartea curentă se potrivește în vreuna dintre ele
            boolean addedToExistingList = false;
            for (ReadingList readingList : readingLists) {
                boolean relatedToCurrentList = readingList.getBooks().stream()
                        .anyMatch(book -> graph.containsEdge(book, currentBook) );//|| book.getColor() == currentBook.getColor());

                if (!relatedToCurrentList) {
                    readingList.getBooks().add(currentBook);
                    addedToExistingList = true;
                    break; // Nu mai avem nevoie să căutăm în alte liste de lectură
                }
            }

            if (!addedToExistingList) {
                readingLists.add(new ReadingList(currentReadingList));
            }
        }
    }*/
  /*  public void printBooksByColor() {
        Map<Integer, List<BookNode>> booksByColor = new HashMap<>();

        // Grupăm cărțile în funcție de culoarea atribuită
        for (BookNode book : graph.vertexSet()) {
            int color = book.getColor();
            if (!booksByColor.containsKey(color)) {
                booksByColor.put(color, new ArrayList<>());
            }
            booksByColor.get(color).add(book);
        }

        // Afișăm cărțile pentru fiecare culoare
        for (Map.Entry<Integer, List<BookNode>> entry : booksByColor.entrySet()) {
            int color = entry.getKey();
            List<BookNode> books = entry.getValue();

            System.out.println("CULOAREA " + color + ":");
            for (BookNode book : books) {
                System.out.println("- " + book.getBook().getName());
            }
            System.out.println();
        }
    }*/
    public void partitionBooks() {
        Map<Integer, List<BookNode>> booksByColor = new HashMap<>();

        // Grupăm cărțile în funcție de culoarea atribuită
        for (BookNode book : graph.vertexSet()) {
            int color = book.getColor();
            if (!booksByColor.containsKey(color)) {
                booksByColor.put(color, new ArrayList<>());
            }
            booksByColor.get(color).add(book);
        }
        int maxLength = 0;
        for (List<BookNode> books : booksByColor.values()) {
            if (books.size() > maxLength) {
                maxLength = books.size();
            }
        }

        for(int i=0;i<maxLength;i++){
            ReadingList newList= new ReadingList();
            readingLists.add(newList);
            for (Map.Entry<Integer, List<BookNode>> entry : booksByColor.entrySet()){
                newList.addBook(entry.getValue().get(i%entry.getValue().size()));
            }
        }

        /*// Afișăm cărțile pentru fiecare culoare
        for (Map.Entry<Integer, List<BookNode>> entry : booksByColor.entrySet()) {
            int color = entry.getKey();
            List<BookNode> books = entry.getValue();

            System.out.println("CULOAREA " + color + ":");
            for (BookNode book : books) {
                System.out.println("- " + book.getBook().getName());
            }
            System.out.println();
        }*/
    }

    public void printReadingLists() {
        System.out.println("\u001B[31m                                          READING LISTS:\u001B[32m");
        System.out.println("\u001B[33m__________________________________________________________________________________________________________\u001B[0m");
        for (int i = 0; i < readingLists.size(); i++) {
            ReadingList currentList = readingLists.get(i);
            System.out.println("\u001B[44m \033[1;30m List " + (i + 1) + ":\033[0m\u001B[0m");
            System.out.println("Creation Timestamp: " + currentList.getCreatedAt());
            System.out.println("Books:");
            for (BookNode book : currentList.getBooks()) {
                System.out.println("- " + book.getBook().getName());
            }
            System.out.println("\u001B[33m__________________________________________________________________________________________________________\u001B[0m");
            System.out.println();
        }
    }

}

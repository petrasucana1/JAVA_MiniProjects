package org.example.Graph;

import org.example.Models.Book;

public class BookNode {
    private Book book;
    private int color;

    public BookNode(Book book) {
        this.book = book;
        this.color = 0;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "BookNode{" +
                "book=" + book +
                ", color=" + color +
                '}';
    }
}

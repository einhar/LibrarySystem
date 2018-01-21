package com.erh.library;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Book {

    private long uniqueID;
    private String title;
    private List<String> authors;
    private int publishedYear;
    private boolean available;


    public long getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(long uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        String result = "";
        for (String author: authors) {
            result += author + "_";
        }
        return result;
    }

    public void setAuthors(String authors) {
        this.authors = Arrays.asList(authors.split("_"));
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "uniqueID=" + uniqueID +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedYear=" + publishedYear +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publishedYear == book.publishedYear &&
                Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, authors, publishedYear);
    }
}

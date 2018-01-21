package com.erh.library;

import java.util.List;

public class Library {

    BookDAO bookDAO;

    public Library(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
        try {
            bookDAO.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewBook(String title, String authors, int year) {
        Book book = new Book();
        book.setAvailable(true);
        book.setTitle(title);
        book.setAuthors(authors);
        book.setPublishedYear(year);

        bookDAO.insertBook(book);
    }

    public void loanBook(long uniqueID) {
        List<Book> books = bookDAO.findBookByProperty(BookSearchType.ID, uniqueID);
        if (books.size() > 0) {
            books.get(0).setAvailable(false);
            bookDAO.updateBook(books.get(0));
        }
    }

    public void returnBook(long uniqueID) {
        List<Book> books = bookDAO.findBookByProperty(BookSearchType.ID, uniqueID);
        if (books.size() > 0) {
            books.get(0).setAvailable(true);
            bookDAO.updateBook(books.get(0));
        }
    }

    public void close() {
        try {
            bookDAO.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> search(BookSearchType searchType, String value) {
        return bookDAO.findBookByProperty(searchType,value);
    }
}

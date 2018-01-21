package com.erh.library;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ApacheDerbyBookDAO implements BookDAO {

    private Connection connection;

    private QueryRunner dbAccess = new QueryRunner();

    private static final List<Book> EMPTY = new ArrayList<>();

    @Override
    public long insertBook(Book book) {
        try {
            long id = dbAccess.insert(connection, "INSERT INTO Books(title,authors,publishedYear,available) VALUES(?,?,?,?)",
                            new ScalarHandler<BigDecimal>(), book.getTitle(), book.getAuthors(), book.getPublishedYear(), book.isAvailable()).longValue();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean updateBook(Book book) {
        try {
            dbAccess.update(connection, "UPDATE Books SET title=?, authors=?, publishedYear=?, available=? WHERE uniqueID=?",
                            book.getTitle(), book.getAuthors(), book.getPublishedYear(), book.isAvailable(), book.getUniqueID());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(Book book) {
        try {
            dbAccess.update(connection, "DELETE FROM Books WHERE uniqueID=?",
                            book.getUniqueID());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> findBookByProperty(BookSearchType searchType, Object value) {

        String whereClause = "";
        String valueClause = "";

        switch (searchType) {
            case ID:
                whereClause = "uniqueID = ?";
                valueClause = value.toString();
                break;
            case TITLE:
                whereClause = "title LIKE ?";
                valueClause = "%" + value.toString() + "%";
                break;
            case AUTHOR:
                whereClause = "authors LIKE ?";
                valueClause = "%" + value.toString() + "%";
                break;
            case PUBLISHED_YEAR:
                whereClause = "publishedYear = ?";
                valueClause = value.toString();
                break;
            case AVAILABLE:
                whereClause = "available = ?";
                valueClause = value.toString();
                break;
            default:
                System.out.println("Unknown search type");
                break;
        }

        try {
            return dbAccess.query(connection, "SELECT * FROM Books WHERE " + whereClause, new BeanListHandler<Book>(Book.class), valueClause);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public List<Book> findAll() {
        try {
            return dbAccess.query(connection, "SELECT * FROM Books ", new BeanListHandler<Book>(Book.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public void setup() throws Exception {
        connection = DriverManager.getConnection("jdbc:derby:books.db;create=true");
        System.out.println("DAO.setup();");

        dbAccess.update(connection, "CREATE TABLE Books ("
                        + "uniqueID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "title VARCHAR(50),"
                        + "authors VARCHAR(100),"
                        + "publishedYear INTEGER,"
                        + "available BOOLEAN)");
        System.out.println("TABLE Books - created.");
    }

    @Override
    public void connect() throws Exception {
        connection = DriverManager.getConnection("jdbc:derby:books.db");
        System.out.println("ApacheDerbyBookDAO.connect();");
    }

    @Override
    public void close() throws Exception {
        connection.close();
        try {
            DriverManager.getConnection("jdbc:derby:books.db;shutdown=true");
        } catch (Exception e) {}
    }
}

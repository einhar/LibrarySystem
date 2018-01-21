package com.erh.library;

import java.util.List;
import java.util.Optional;

public interface BookDAO extends DAO {
    public long insertBook(Book book);
    public boolean updateBook(Book book);
    public boolean deleteBook(Book book);

    public List<Book> findBookByProperty(BookSearchType searchType, Object value);
    public List<Book> findAll();

}

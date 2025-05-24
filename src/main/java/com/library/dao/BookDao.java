package com.library.dao;

import com.library.model.Book;

public interface BookDao {
    Book findByIsbn(String isbn);

    void save(Book book);
}

package com.library.dao;

import com.library.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDaoMemoryImpl implements BookDao {
    private List<Book> books = new ArrayList<>();

    @Override
    public Book findByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Book book) {
        books.add(book);
    }
}

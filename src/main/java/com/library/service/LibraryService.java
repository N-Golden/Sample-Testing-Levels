package com.library.service;

import com.library.dao.BookDao;
import com.library.dao.UserDao;
import com.library.model.Book;
import com.library.model.User;

public class LibraryService {
    private BookDao bookDao;
    private UserDao userDao;

    public LibraryService(BookDao bookDao, UserDao userDao) {
        this.bookDao = bookDao;
        this.userDao = userDao;
    }

    // find book available
    public boolean findBookAvailable(String isbn) {
        Book book = bookDao.findByIsbn(isbn);
        if (book == null || !book.isAvailable()) {
            return false;
        }
        book.setAvailable(false);
        return true;
    }

    // borrow book for user
    public boolean borrowBook(String isbn, String userName) {
        User user = userDao.findByUsername(userName);
        if(user == null) {
            return false;
        }

        Book book = bookDao.findByIsbn(isbn);
        if (book == null || !book.isAvailable()) {
            return false;
        }
        book.setAvailable(false);
        return true;
    }
}

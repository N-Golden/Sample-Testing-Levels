package com.library.service;

import com.library.dao.BookDao;
import com.library.dao.UserDao;
import com.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryServiceTest {

    private BookDao bookDao;
    private UserDao userDao;
    private LibraryService libraryService;

    @BeforeEach
    void setup() {
        bookDao = Mockito.mock(BookDao.class); // mock an instance
        userDao = Mockito.mock(UserDao.class); // mock an instance
        libraryService = new LibraryService(bookDao, userDao);
    }

    @Test
    void testFindBookAvailable() {
        Mockito.when(bookDao.findByIsbn("ISBN001")).thenReturn(new com.library.model.Book("ISBN001", "Java Programming"));
        boolean result = libraryService.findBookAvailable("ISBN001");
        assertTrue(result);
    }

    @Test
    void testFindBookUnavailable() {
        Book book = new Book("ISBN001", "Java Programming");
        book.setAvailable(false);
        Mockito.when(bookDao.findByIsbn("ISBN001")).thenReturn(book);
        boolean result = libraryService.findBookAvailable("ISBN001");
        assertFalse(result);
    }

    @Test
    void testBrowBookSuccess() {
        Mockito.when(userDao.findByUsername("user1")).thenReturn(new com.library.model.User("user1", "<PASSWORD>"));
        Mockito.when(bookDao.findByIsbn("ISBN001")).thenReturn(new com.library.model.Book("ISBN001", "Java Programming"));
        boolean result = libraryService.borrowBook("ISBN001", "user1");
        assertTrue(result);
    }

}

package com.library.integration;

import com.library.dao.BookDao;
import com.library.dao.BookDaoMemoryImpl;
import com.library.dao.UserDao;
import com.library.dao.UserDaoMemoryImpl;
import com.library.model.Book;
import com.library.model.User;
import com.library.service.LibraryService;
import com.library.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// test behavior -> real data
public class UserLibraryTest {
    private UserDao userDao;
    private BookDao bookDao;
    private UserService userService;
    private LibraryService libraryService;

    @BeforeEach
    void setup() {
        userDao = new UserDaoMemoryImpl();
        bookDao = new BookDaoMemoryImpl();
        userService = new UserService(userDao);
        libraryService = new LibraryService(bookDao, userDao);

        bookDao.save(new Book("ISBN001", "Java Programming"));
    }

    @Test
    void loginBorrowSuccess(){
        //register, login success
        userService.register("user1", "pass1");
        User user = userService.login("user1", "pass1");
        assertNotNull(user);

        // borrow book success
        boolean result = libraryService.borrowBook("ISBN001", user.getUsername());
        assertTrue(result);
    }

}

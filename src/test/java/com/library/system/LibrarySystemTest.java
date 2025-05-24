package com.library.system;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.library.dao.UserDao;
import com.library.dao.UserDaoMemoryImpl;
import com.library.dao.BookDao;
import com.library.dao.BookDaoMemoryImpl;
import com.library.model.User;
import com.library.model.Book;
import com.library.service.UserService;
import com.library.service.LibraryService;

public class LibrarySystemTest {

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

        // Thêm 1 số sách mẫu
        bookDao.save(new Book("ISBN001", "Java Programming"));
        bookDao.save(new Book("ISBN002", "Data Structures"));
    }

    @Test
    void testFullProcessBorrowBook() {
        // 1. Đăng ký user
        assertTrue(userService.register("alice", "alice123"));

        // 2. Đăng nhập
        User user = userService.login("alice", "alice123");
        assertNotNull(user);

        // 3. Mượn sách thành công
        boolean borrowResult = libraryService.borrowBook("ISBN001", user.getUsername());
        assertTrue(borrowResult);

        // 4. Mượn sách lần 2 thất bại vì sách không còn sẵn
        boolean borrowAgain = libraryService.borrowBook("ISBN001", user.getUsername());
        assertFalse(borrowAgain);

        // 5. Mượn sách khác thành công
        assertTrue(libraryService.borrowBook("ISBN002", user.getUsername()));
    }
}

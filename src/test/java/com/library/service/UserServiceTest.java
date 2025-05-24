package com.library.service;

import com.library.dao.UserDao;
import com.library.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// test logic function -> mock behavior
public class UserServiceTest {
    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void setup() {
        userDao = Mockito.mock(UserDao.class); // mock an instance
        userService = new UserService(userDao);
    }

    // register
    @Test
    void testRegisterSuccess() {
        Mockito.when(userDao.findByUsername("user1")).thenReturn(null);
        boolean result = userService.register("user1", "pass123");
        assertTrue(result);
    }

    @Test
    void testRegisterDuplicateFail() {
        Mockito.when(userDao.findByUsername("userExist")).thenReturn(new User("userExist", "pass123"));
        boolean result = userService.register("userExist", "otherpass");
        assertFalse(result);
    }

    // test login()
    @Test
    void testLoginSuccess() {
        User mockUser = new User("user1", "pass123");
        Mockito.when(userDao.findByUsername("user1")).thenReturn(mockUser); // mock an action of instance

        User result = userService.login("user1", "pass123");
        assertNotNull(result);
        assertEquals("user1", result.getUsername());
    }

    @Test
    void testLoginFailWrongPassword() {
        User mockUser = new User("user1", "pass123");
        Mockito.when(userDao.findByUsername("user1")).thenReturn(mockUser);

        User result = userService.login("user1", "wrongpass");
        assertNull(result);
    }

    @Test
    void testLoginFailUserNotFound() {
        Mockito.when(userDao.findByUsername("unknown")).thenReturn(null);
        User result = userService.login("unknown", "pass123");
        assertNull(result);
    }
}

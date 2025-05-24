package com.library.dao;

import com.library.model.User;

public interface UserDao {
    User findByUsername(String username);
    void save(User user);
}

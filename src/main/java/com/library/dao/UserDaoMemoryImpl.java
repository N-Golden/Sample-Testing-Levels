package com.library.dao;

import com.library.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMemoryImpl implements UserDao {
    private final List<User> users = new ArrayList<>();

    @Override
    public User findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}

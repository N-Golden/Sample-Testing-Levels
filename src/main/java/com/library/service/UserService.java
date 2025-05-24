package com.library.service;

import com.library.dao.UserDao;
import com.library.model.User;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao dao) {
        this.userDao = dao;
    }

    /**
     * Đăng ký user mới: trả về false nếu username đã tồn tại
     */
    public boolean register(String username, String password) {
        if (userDao.findByUsername(username) != null)
            return false;

        userDao.save(new User(username, password));
        return true;
    }

    /**
     * Đăng nhập: trả về User nếu thành công, null nếu sai thông tin
     */
    public User login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}

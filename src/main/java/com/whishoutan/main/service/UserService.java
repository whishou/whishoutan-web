package com.whishoutan.main.service;

import com.whishoutan.main.entity.User;

public interface UserService {
    boolean findUserByUserAndPassword(User user);
}

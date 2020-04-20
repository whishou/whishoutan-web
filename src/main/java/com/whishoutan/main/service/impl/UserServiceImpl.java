package com.whishoutan.main.service.impl;

import com.whishoutan.main.dao.UserMapper;
import com.whishoutan.main.entity.User;
import com.whishoutan.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean findUserByUserAndPassword(User user) {
        User temp = userMapper.findUserByUserAndPassword(user);
        //System.out.println(temp.toString());

        return temp == null?false:true;
    }

}

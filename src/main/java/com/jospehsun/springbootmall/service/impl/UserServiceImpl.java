package com.jospehsun.springbootmall.service.impl;

import com.jospehsun.springbootmall.dao.UserDao;
import com.jospehsun.springbootmall.dto.UserRegisterRequest;
import com.jospehsun.springbootmall.model.User;
import com.jospehsun.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setCreatedDate(new Date());
        userRegisterRequest.setLastModifiedDate(new Date());
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}

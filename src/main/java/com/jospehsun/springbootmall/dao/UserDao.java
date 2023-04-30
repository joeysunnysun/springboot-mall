package com.jospehsun.springbootmall.dao;

import com.jospehsun.springbootmall.dto.UserRegisterRequest;
import com.jospehsun.springbootmall.model.User;

public interface UserDao {
    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

}

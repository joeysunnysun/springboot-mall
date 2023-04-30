package com.jospehsun.springbootmall.service;

import com.jospehsun.springbootmall.dto.UserRegisterRequest;
import com.jospehsun.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}

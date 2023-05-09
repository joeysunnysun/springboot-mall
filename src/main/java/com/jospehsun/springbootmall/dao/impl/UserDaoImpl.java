package com.jospehsun.springbootmall.dao.impl;

import com.jospehsun.springbootmall.dao.UserDao;
import com.jospehsun.springbootmall.dto.UserRegisterRequest;
import com.jospehsun.springbootmall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "INSERT INTO `user` (email, password, createdDate, lastModifiedDate) " +
                "VALUES (:email, :password, :createdDate, :lastModifiedDate) ";

//        Map<String, Object> map = new HashMap<>();
//        map.put("email", userRegisterRequest.getEmail());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userRegisterRequest);

        namedParameterJdbcTemplate.update(sql, params, keyHolder);
        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT userId, email, password, createdDate, lastModifiedDate " +
                "FROM `user` WHERE email = :email ";

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(User.class));

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User getUserById(Integer userId) {
        String sql = "SELECT userId, email, password, createdDate, lastModifiedDate " +
                "FROM `user` WHERE userId = :userId ";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(User.class));

        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}

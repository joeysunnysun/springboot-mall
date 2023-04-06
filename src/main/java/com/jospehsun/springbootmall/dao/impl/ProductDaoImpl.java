package com.jospehsun.springbootmall.dao.impl;

import com.jospehsun.springbootmall.dao.ProductDao;
import com.jospehsun.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT productId, productName, category, imageUrl, price, stock, description, " +
                "createdDate, lastModifiedDate FROM product WHERE productId = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        Product product = namedParameterJdbcTemplate.queryForObject(sql, map, new BeanPropertyRowMapper<>(Product.class));
        return product;
    }
}

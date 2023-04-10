package com.jospehsun.springbootmall.dao.impl;

import com.jospehsun.springbootmall.dao.ProductDao;
import com.jospehsun.springbootmall.dto.ProductRequest;
import com.jospehsun.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
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

    @Override
    public Integer createProductById(ProductRequest productRequest) {
        String sql = "INSERT INTO product(productName, category, imageUrl, price, stock, " +
                "description, createdDate, lastModifiedDate) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate) ";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;
    }
}

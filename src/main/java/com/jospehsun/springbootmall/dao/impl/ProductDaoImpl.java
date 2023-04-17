package com.jospehsun.springbootmall.dao.impl;

import com.jospehsun.springbootmall.dao.ProductDao;
import com.jospehsun.springbootmall.dto.ProductQueryParams;
import com.jospehsun.springbootmall.dto.ProductRequest;
import com.jospehsun.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "SELECT productId, productName, category, imageUrl, price, stock, description, " +
                "createdDate, lastModifiedDate " +
                "FROM product WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();

        if (productQueryParams.getCategory() != null) {
            sql = sql + "AND category = :category ";
            map.put("category", productQueryParams.getCategory().toString());
        }

        if (productQueryParams.getSearch() != null) {
            sql = sql + "AND productName LIKE :search ";
            map.put("search", "%" + productQueryParams.getSearch() + "%");
        }

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(Product.class));
        return productList;
    }

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
                "VALUES (:productName, :category.name, :imageUrl, :price, :stock, :description, " +
                ":createdDate, :lastModifiedDate) ";

        /*Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);*/

        KeyHolder keyHolder = new GeneratedKeyHolder();

        /*namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);*/
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(productRequest);
        namedParameterJdbcTemplate.update(sql, params, keyHolder);
        int productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET productName = :productName, category = :category.name, imageUrl = :imageUrl, " +
                "price = :price, stock = :stock, description = :description, lastModifiedDate = :lastModifiedDate" +
                " WHERE productId = :productId ";

        /*Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());*/
//        productRequest.setProductId(productId);
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(productRequest);
        namedParameterJdbcTemplate.update(sql, params);
        /*namedParameterJdbcTemplate.update(sql, map);*/
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE productId = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);

    }
}

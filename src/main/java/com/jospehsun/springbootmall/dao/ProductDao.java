package com.jospehsun.springbootmall.dao;

import com.jospehsun.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}

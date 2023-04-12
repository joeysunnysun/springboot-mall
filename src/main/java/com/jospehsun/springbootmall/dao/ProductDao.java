package com.jospehsun.springbootmall.dao;

import com.jospehsun.springbootmall.dto.ProductRequest;
import com.jospehsun.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProductById(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}

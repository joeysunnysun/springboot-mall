package com.jospehsun.springbootmall.service;

import com.jospehsun.springbootmall.constant.ProductCategory;
import com.jospehsun.springbootmall.dto.ProductRequest;
import com.jospehsun.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    List<Product> getProducts(ProductCategory category, String search);
}

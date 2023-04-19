package com.jospehsun.springbootmall.service;

import com.jospehsun.springbootmall.dto.ProductQueryParams;
import com.jospehsun.springbootmall.dto.ProductRequest;
import com.jospehsun.springbootmall.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer countProduct(ProductQueryParams productQueryParams);
}

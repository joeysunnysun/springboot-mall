package com.jospehsun.springbootmall.service.impl;

import com.jospehsun.springbootmall.dao.ProductDao;
import com.jospehsun.springbootmall.dto.ProductRequest;
import com.jospehsun.springbootmall.model.Product;
import com.jospehsun.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProductById(productRequest);
    }
}

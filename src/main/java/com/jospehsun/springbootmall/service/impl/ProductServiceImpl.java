package com.jospehsun.springbootmall.service.impl;

import com.jospehsun.springbootmall.dao.ProductDao;
import com.jospehsun.springbootmall.dto.ProductRequest;
import com.jospehsun.springbootmall.model.Product;
import com.jospehsun.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
        productRequest.setCreatedDate(new Date());
        return productDao.createProductById(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        productRequest.setLastModifiedDate(new Date());
        productDao.updateProduct(productId, productRequest);
    }
}

package com.ing.ingproject.service;

import com.ing.ingproject.model.Product;
import com.ing.ingproject.model.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDatabase productDatabase;

    @Autowired
    public ProductServiceImpl(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDatabase.getProducts();
    }
}

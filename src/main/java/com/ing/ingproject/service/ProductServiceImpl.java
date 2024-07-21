package com.ing.ingproject.service;

import com.ing.ingproject.model.Product;
import com.ing.ingproject.model.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDatabase productDatabase;

    @Autowired
    public ProductServiceImpl(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    @Override
    public Set<Product> getAllProducts() {
        return productDatabase.getProducts();
    }

    @Override
    public Optional<Product> getProductByName(String productName) {
        return productDatabase.getProducts().stream()
                .filter(product -> productName.equals(product.productName()))
                .findFirst();
    }

    @Override
    public void addProduct(Product product) {
        productDatabase.addProduct(product);
    }
}

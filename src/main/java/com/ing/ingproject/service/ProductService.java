package com.ing.ingproject.service;

import com.ing.ingproject.model.Product;
import com.ing.ingproject.model.ProductUpdateRequest;

import java.util.Optional;
import java.util.Set;

public interface ProductService {

    Set<Product> getAllProducts();

    Optional<Product> getProductByName(String productName);

    void addProduct(Product product);

    Optional<Product> updateProduct(String productName, ProductUpdateRequest updatedProduct);

    boolean deleteProduct(String productName);
}

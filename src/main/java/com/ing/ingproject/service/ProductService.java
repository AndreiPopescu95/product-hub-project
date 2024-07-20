package com.ing.ingproject.service;

import com.ing.ingproject.model.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductService {

    Set<Product> getAllProducts();

    Optional<Product> getProductByName(String productName);
}

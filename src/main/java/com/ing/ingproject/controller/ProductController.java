package com.ing.ingproject.controller;

import com.ing.ingproject.exception.ProductNotFoundException;
import com.ing.ingproject.model.Product;
import com.ing.ingproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/v1")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/all")
    public Set<Product> allProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{productName}")
    public Product findProduct(@PathVariable String productName) {
        Optional<Product> product = productService.getProductByName(productName);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException("Product not found with name: " + productName);
        }
    }
}

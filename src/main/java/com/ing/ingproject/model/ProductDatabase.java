package com.ing.ingproject.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Data
@Component
public class ProductDatabase {
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product){
        products.add(product);
    }

    public Optional<Product> updateProduct(String productName, ProductUpdateRequest productPatch){
        Optional<Product> existingProductOptional = products.stream()
                .filter(product -> productName.equals(product.productName()))
                .findFirst();

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            Product updatedProduct = new Product(
                    existingProduct.productName(),
                    productPatch.getPrice() != null ? productPatch.getPrice() : existingProduct.price()
            );

            products.remove(existingProduct);
            products.add(updatedProduct);

            return Optional.of(updatedProduct);
        }
        return Optional.empty();
    }

}

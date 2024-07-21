package com.ing.ingproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.ing.ingproject.utils.ProductTestUtils.createDefaultProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ProductDatabaseTest {

    private static final String DEFAULT_PRODUCT_NAME = "testProduct";

    @InjectMocks
    private ProductDatabase productDatabase;

    @BeforeEach
    public void setUp() {
        Set<Product> products = new HashSet<>();
        products.add(createDefaultProduct());
        productDatabase.setProducts(products);
    }

    @Test
    void updateProduct() {
        double price = 3.0;
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(price);

        Optional<Product> updatedProduct = productDatabase.updateProduct(DEFAULT_PRODUCT_NAME, productUpdateRequest);

        assertTrue(updatedProduct.isPresent());
        assertEquals(DEFAULT_PRODUCT_NAME, updatedProduct.get().productName());
        assertEquals(price, updatedProduct.get().price());
    }
}
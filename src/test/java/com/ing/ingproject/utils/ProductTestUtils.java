package com.ing.ingproject.utils;

import com.ing.ingproject.model.Product;

public class ProductTestUtils {

    private static final Long DEFAULT_PRODUCT_ID = 1L;
    private static final String DEFAULT_PRODUCT_NAME = "testProduct";
    private static final Double DEFAULT_PRODUCT_PRICE = 9.99;


    public static Product createProduct(Long productId, String productName, double price) {
        return new Product(productId, productName, price);
    }

    public static Product createDefaultProduct() {
        return new Product(DEFAULT_PRODUCT_ID, DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_PRICE);
    }
}

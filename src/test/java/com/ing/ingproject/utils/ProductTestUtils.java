package com.ing.ingproject.utils;

import com.ing.ingproject.model.Product;
import com.ing.ingproject.model.ProductUpdateRequest;

public class ProductTestUtils {

    private static final String DEFAULT_PRODUCT_NAME = "testProduct";
    private static final Double DEFAULT_PRODUCT_PRICE = 9.99;


    public static Product createProduct(String productName, double price) {
        return new Product(productName, price);
    }

    public static Product createDefaultProduct() {
        return new Product(DEFAULT_PRODUCT_NAME, DEFAULT_PRODUCT_PRICE);
    }

    public static ProductUpdateRequest createDefaultProductUpdate() {
        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest();
        productUpdateRequest.setPrice(5.0);
        return productUpdateRequest;
    }
}

package com.ing.ingproject.service;

import com.ing.ingproject.model.Product;
import com.ing.ingproject.model.ProductDatabase;
import com.ing.ingproject.model.ProductUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static com.ing.ingproject.utils.ProductTestUtils.createDefaultProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private static final String DEFAULT_PRODUCT_NAME = "testProduct";

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDatabase productDatabase;

    @Test
    void getAllProductsTest() {
        Set<Product> expectedProducts = generateDefaultProductList();

        when(productDatabase.getProducts()).thenReturn(expectedProducts);

        Set<Product> resultProducts = productService.getAllProducts();

        assertEquals(expectedProducts, resultProducts);
    }

    @Test
    void getProductByNameTest() {
        Product expectedProduct = createDefaultProduct();
        Set<Product> expectedProducts = Set.of(expectedProduct);
        when(productDatabase.getProducts()).thenReturn(expectedProducts);

        Optional<Product> actualProductOptional = productService.getProductByName(DEFAULT_PRODUCT_NAME);

        assertTrue(actualProductOptional.isPresent());
        assertEquals(expectedProduct, actualProductOptional.get());
    }

    @Test
    void addProductTest() {
        Product expectedProduct = createDefaultProduct();

        productService.addProduct(expectedProduct);

        verify(productDatabase).addProduct(expectedProduct);
    }

    private Set<Product> generateDefaultProductList() {
        Product product = createDefaultProduct();

        return Set.of(product);
    }

    @Test
    void updateProduct() {
        double productPrice = 5.0;
        ProductUpdateRequest updatedProduct = new ProductUpdateRequest(productPrice);

        when(productDatabase.updateProduct(DEFAULT_PRODUCT_NAME, updatedProduct))
                .thenReturn(Optional.of(new Product(DEFAULT_PRODUCT_NAME, updatedProduct.getPrice())));

        Optional<Product> finalProduct = productService.updateProduct(DEFAULT_PRODUCT_NAME, updatedProduct);

        assertTrue(finalProduct.isPresent());
        assertEquals(productPrice, finalProduct.get().price());
    }
}
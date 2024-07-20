package com.ing.ingproject.service;

import com.ing.ingproject.model.Product;
import com.ing.ingproject.model.ProductDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.ing.ingproject.utils.ProductTestUtils.createDefaultProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDatabase productDatabase;

    @Test
    void getAllProductsTest() {
        List<Product> expectedProducts = generateDefaultProductList();

        when(productDatabase.getProducts()).thenReturn(expectedProducts);

        List<Product> resultProducts = productService.getAllProducts();

        assertEquals(expectedProducts, resultProducts);
    }

    private List<Product> generateDefaultProductList() {
        Product product = createDefaultProduct();

        return List.of(product);
    }
}
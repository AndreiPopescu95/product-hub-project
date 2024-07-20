package com.ing.ingproject.controller;


import com.ing.ingproject.model.Product;
import com.ing.ingproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.ing.ingproject.utils.ProductTestUtils.createProduct;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final String GET_ALL_PRODUCTS = "/v1/products/all";
    private static final String FIND_PRODUCT = "/v1/products/{productName}";

    private final String PRODUCT_1 = "Product 1";
    private final String PRODUCT_2 = "Product 2";
    private final Double PRICE_1 = 10.0;
    private final Double PRICE_2 = 20.0;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser
    public void getAllProductsTest() throws Exception {
        when(productService.getAllProducts()).thenReturn(createProductList());

        mockMvc.perform(get(GET_ALL_PRODUCTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].productName", is(PRODUCT_1)))
                .andExpect(jsonPath("$[0].price", is(PRICE_1)))
                .andExpect(jsonPath("$[1].productName", is(PRODUCT_2)))
                .andExpect(jsonPath("$[1].price", is(PRICE_2)));
    }

    @Test
    @WithMockUser
    public void findProductTest() throws Exception {
        when(productService.getProductByName(PRODUCT_1)).thenReturn(Optional.of(createProduct(PRODUCT_1, PRICE_1)));

        mockMvc.perform(get(FIND_PRODUCT,PRODUCT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is(PRODUCT_1)))
                .andExpect(jsonPath("$.price", is(PRICE_1)));
    }

    @Test
    @WithMockUser
    public void findProductNoProductTest() throws Exception {
        when(productService.getProductByName(PRODUCT_1)).thenReturn(Optional.empty());

        mockMvc.perform(get(FIND_PRODUCT,PRODUCT_1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is(String.format("Product not found with name: %s", PRODUCT_1))));
    }

    private Set<Product> createProductList() {
        Set<Product> products = new HashSet<>();
        products.add(createProduct(PRODUCT_1, PRICE_1));
        products.add(createProduct(PRODUCT_2, PRICE_2));
        return products;
    }
}
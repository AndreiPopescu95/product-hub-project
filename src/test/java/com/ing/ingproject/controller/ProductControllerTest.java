package com.ing.ingproject.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.ingproject.model.Product;
import com.ing.ingproject.model.ProductUpdateRequest;
import com.ing.ingproject.security.SecurityConfig;
import com.ing.ingproject.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.ing.ingproject.utils.ProductTestUtils.createDefaultProduct;
import static com.ing.ingproject.utils.ProductTestUtils.createDefaultProductUpdate;
import static com.ing.ingproject.utils.ProductTestUtils.createProduct;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ProductController.class, SecurityConfig.class})
class ProductControllerTest {

    private static final String GET_ALL_PRODUCTS = "/v1/products/all";
    private static final String FIND_PRODUCT = "/v1/products/{productName}";
    private static final String UPDATE_PRODUCT = "/v1/products/update/{productName}";
    private static final String DELETE_PRODUCT = "/v1/products/delete/{productName}";
    private static final String ADD_PRODUCT = "/v1/products/add";
    private static final String PRODUCT_NOT_FOUND = "Product not found with name: %s";

    private final String PRODUCT_1 = "Product 1";
    private final String PRODUCT_2 = "Product 2";
    private final Double PRICE_1 = 10.0;
    private final Double PRICE_2 = 20.0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    @WithMockUser(username = "user")
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
    @WithMockUser(username = "user")
    public void findProductTest() throws Exception {
        when(productService.getProductByName(PRODUCT_1)).thenReturn(Optional.of(createProduct(PRODUCT_1, PRICE_1)));

        mockMvc.perform(get(FIND_PRODUCT,PRODUCT_1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", is(PRODUCT_1)))
                .andExpect(jsonPath("$.price", is(PRICE_1)));
    }

    @Test
    @WithMockUser(username = "user")
    public void findProductNoProductTest() throws Exception {
        when(productService.getProductByName(PRODUCT_1)).thenReturn(Optional.empty());

        mockMvc.perform(get(FIND_PRODUCT,PRODUCT_1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", is(String.format(PRODUCT_NOT_FOUND, PRODUCT_1))));
    }

    @Test
    @WithMockUser(username = "user")
    public void addProductTest() throws Exception {
        Product product = createDefaultProduct();

        mockMvc.perform(post(ADD_PRODUCT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productName").value(product.productName()))
                .andExpect(jsonPath("$.price").value(product.price()));
    }

    @Test
    @WithMockUser(username = "user")
    public void updateProductTest() throws Exception {
        ProductUpdateRequest productUpdate = createDefaultProductUpdate();
        Product product = createProduct(PRODUCT_1, productUpdate.getPrice());

        when(productService.updateProduct(PRODUCT_1, productUpdate)).thenReturn(Optional.of(product));

        mockMvc.perform(put(UPDATE_PRODUCT, PRODUCT_1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value(PRODUCT_1))
                .andExpect(jsonPath("$.price").value(productUpdate.getPrice()));
    }

    @Test
    @WithMockUser(username = "user")
    public void deleteProductTest() throws Exception {
        when(productService.deleteProduct(PRODUCT_1)).thenReturn(true);

        mockMvc.perform(delete(DELETE_PRODUCT, PRODUCT_1))
                .andExpect(status().isNoContent());
    }

    private Set<Product> createProductList() {
        Set<Product> products = new HashSet<>();
        products.add(createProduct(PRODUCT_1, PRICE_1));
        products.add(createProduct(PRODUCT_2, PRICE_2));
        return products;
    }
}
package com.ing.ingproject.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Product {
    private String productId;
    private String productName;
    private String price;
}

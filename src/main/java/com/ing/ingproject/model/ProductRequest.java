package com.ing.ingproject.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ProductRequest {
    private String productName;
    private Double price;
}

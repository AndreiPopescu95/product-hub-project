package com.ing.ingproject.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ProductDatabase {
    private List<Product> products;
}

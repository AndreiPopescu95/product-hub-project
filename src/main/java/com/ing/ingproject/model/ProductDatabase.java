package com.ing.ingproject.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
public class ProductDatabase {
    private Set<Product> products;
}

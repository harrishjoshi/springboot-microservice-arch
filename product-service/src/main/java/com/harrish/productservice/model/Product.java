package com.harrish.productservice.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@Data
@Builder
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private BigDecimal price;
}

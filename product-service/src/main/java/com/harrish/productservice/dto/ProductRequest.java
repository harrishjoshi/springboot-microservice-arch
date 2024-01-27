package com.harrish.productservice.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotEmpty(message = "Name cannot be empty")
        String name,
        @NotEmpty(message = "Description cannot be empty")
        String description,
        @NotNull(message = "Price cannot be null")
        @Digits(integer = 10, fraction = 4, message = "Invalid price format")
        @Positive(message = "Price must be a greater than zero")
        BigDecimal price
) {
}

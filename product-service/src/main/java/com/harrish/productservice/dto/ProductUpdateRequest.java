package com.harrish.productservice.dto;

import java.math.BigDecimal;

public record ProductUpdateRequest(
        String id,
        String name,
        String description,
        BigDecimal price
) {
}

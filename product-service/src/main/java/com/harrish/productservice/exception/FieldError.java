package com.harrish.productservice.exception;

public record FieldError(
        String field,
        String error
) {
}

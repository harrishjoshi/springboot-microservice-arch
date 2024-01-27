package com.harrish.productservice.config;

import com.harrish.productservice.exception.FieldError;
import com.harrish.productservice.exception.ProductNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        var fieldErrors = new ArrayList<FieldError>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> fieldErrors.add(new FieldError(error.getField(), error.getDefaultMessage())));

        var response = new HashMap<String, Object>();
        response.put("timestamp", OffsetDateTime.now());
        response.put("status", BAD_REQUEST.value());
        response.put("error", BAD_REQUEST.getReasonPhrase());
        response.put("path", request.getRequestURI());

        if (!fieldErrors.isEmpty()) {
            response.put("errorDetails", fieldErrors);
        }

        return new ResponseEntity<>(response, BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> notValid(ProductNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), NOT_FOUND);
    }
}

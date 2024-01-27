package com.harrish.productservice.cotroller;

import com.harrish.productservice.dto.ProductRequest;
import com.harrish.productservice.dto.ProductResponse;
import com.harrish.productservice.dto.ProductUpdateRequest;
import com.harrish.productservice.exception.ProductNotFoundException;
import com.harrish.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping("{id}")
    ProductResponse findById(@PathVariable String id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @GetMapping
    List<ProductResponse> findAllProducts() {
        return productService.findAllProducts();
    }

    @PutMapping
    void updateProduct(@RequestBody ProductUpdateRequest updateRequest) throws ProductNotFoundException {
        productService.updateProduct(updateRequest);
    }

    @DeleteMapping("{id}")
    void deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        productService.deleteProduct(id);
    }
}

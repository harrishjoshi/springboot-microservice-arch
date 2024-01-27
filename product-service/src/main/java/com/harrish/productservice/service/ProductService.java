package com.harrish.productservice.service;

import com.harrish.productservice.dto.ProductRequest;
import com.harrish.productservice.dto.ProductResponse;
import com.harrish.productservice.dto.ProductUpdateRequest;
import com.harrish.productservice.exception.ProductNotFoundException;
import com.harrish.productservice.model.Product;
import com.harrish.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductRequest productRequest) {
        log.info("Product request: {}", productRequest);
        var product = Product.builder().build();
        BeanUtils.copyProperties(productRequest, product);

        productRepository.save(product);
        log.info("Product with id [{}] saved successfully.", product.getId());
    }

    public ProductResponse findById(String id) throws ProductNotFoundException {
        var productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("Product with id [" + id + "] not found.");
        }

        return this.mapToProductResponse(productOpt.get());
    }

    public List<ProductResponse> findAllProducts() {
        log.info("Find all product:");
        return productRepository.findAll()
                .stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public void updateProduct(ProductUpdateRequest updateRequest) throws ProductNotFoundException {
        var productOpt = productRepository.findById(updateRequest.id());
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("Product with id [" + updateRequest.id() + "] not found.");
        }

        log.info("Product update request: {}", updateRequest);

        var product = productOpt.get();
        BeanUtils.copyProperties(updateRequest, product);

        productRepository.save(product);
    }

    public void deleteProduct(String id) throws ProductNotFoundException {
        var productOpt = productRepository.findById(id);
        if (productOpt.isEmpty()) {
            throw new ProductNotFoundException("Product with id [" + id + "] not found.");
        }

        log.info("Delete product with id: {}", id);

        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

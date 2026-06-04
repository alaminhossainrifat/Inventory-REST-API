package com.rifat.Inventory.service;

import com.rifat.Inventory.dto.*;
import com.rifat.Inventory.entity.Product;
import com.rifat.Inventory.exception.ResourceNotFoundException;
import com.rifat.Inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(ProductRequest req) {
        Product p = Product.builder()
                .name(req.getName())
                .category(req.getCategory())
                .quantity(req.getQuantity())
                .price(req.getPrice())
                .build();
        return toResponse(productRepository.save(p));
    }

    public ProductResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse update(Long id, ProductRequest req) {
        Product p = findOrThrow(id);
        p.setName(req.getName());
        p.setCategory(req.getCategory());
        p.setQuantity(req.getQuantity());
        p.setPrice(req.getPrice());
        return toResponse(productRepository.save(p));
    }

    public void delete(Long id) {
        findOrThrow(id);
        productRepository.deleteById(id);
    }

    private Product findOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    private ProductResponse toResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .category(p.getCategory())
                .quantity(p.getQuantity())
                .price(p.getPrice())
                .build();
    }
}

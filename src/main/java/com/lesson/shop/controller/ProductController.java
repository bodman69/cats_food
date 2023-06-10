package com.lesson.shop.controller;

import com.lesson.shop.model.entity.ProductEntity;
import com.lesson.shop.model.request.ProductRequest;
import com.lesson.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductEntity> getAll(@Valid @RequestParam(required = false) String name,
                                      @Valid @RequestParam(required = false) BigDecimal price) {
        return productService.getAll(name, price);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ResponseEntity<Void> create(@RequestBody ProductRequest request) {
        productService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ProductRequest request) {
        productService.update(id, request);

        return ResponseEntity.noContent().build();
    }
}

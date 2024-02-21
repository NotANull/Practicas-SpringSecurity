package com.notanull.SpringSecurityCourse.controller;

import com.notanull.SpringSecurityCourse.dto.request.SaveProductDto;
import com.notanull.SpringSecurityCourse.persistence.entity.Product;
import com.notanull.SpringSecurityCourse.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {

        Page<Product> productPage = productService.findAll(pageable);

        if (productPage.hasContent()) {
            return ResponseEntity.ok(productPage);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findOneById(@PathVariable Long productId) {

        Optional<Product> product = productService.findOneById(productId);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid SaveProductDto saveProductDto) {
        Product product = productService.createOne(saveProductDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateOneById(@PathVariable Long productId, @RequestBody @Valid SaveProductDto saveProductDto) {
        Product product = productService.updateOneById(productId,saveProductDto);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{productId}/disabled")
    public ResponseEntity<Product> disableOneById(@PathVariable Long productId) {
        Product product = productService.disableOneById(productId);
        return ResponseEntity.ok(product);
    }
}

package org.example.dtn2509productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtn2509productservice.dto.request.CreateProductRequest;
import org.example.dtn2509productservice.dto.request.ProductFilter;
import org.example.dtn2509productservice.dto.response.ProductResponse;
import org.example.dtn2509productservice.service.impl.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(productService.create(createProductRequest));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductResponse>> search (@Valid @RequestBody ProductFilter productFilter) {
        return ResponseEntity.ok(productService.search(productFilter));
    }
}

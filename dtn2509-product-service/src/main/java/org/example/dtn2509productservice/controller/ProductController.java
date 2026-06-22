package org.example.dtn2509productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtn2509productservice.dto.request.CreateProductRequest;
import org.example.dtn2509productservice.dto.request.ProductFilter;
import org.example.dtn2509productservice.dto.request.StockProductRequest;
import org.example.dtn2509productservice.dto.response.ProductResponse;
import org.example.dtn2509productservice.service.IProducts;
import org.example.dtn2509productservice.service.impl.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProducts iProducts;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(iProducts.create(createProductRequest));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(iProducts.findAll());
    }

    @PostMapping("/search")
    public ResponseEntity<List<ProductResponse>> search (@Valid @RequestBody ProductFilter productFilter) {
        return ResponseEntity.ok(iProducts.search(productFilter));
    }

    @PutMapping("/stock")
    public ResponseEntity<Boolean> stockProduct (@Valid @RequestBody StockProductRequest stockProductRequest) {
        return ResponseEntity.ok(iProducts.stockProduct(stockProductRequest));
    }
}

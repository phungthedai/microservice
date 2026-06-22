package org.example.dtn2509productservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtn2509productservice.dto.request.CreateCategoryRequest;
import org.example.dtn2509productservice.dto.response.CategoryResponse;
import org.example.dtn2509productservice.service.ICategory;
import org.example.dtn2509productservice.service.impl.CategoryService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("v1/category")
public class CategoryController {

    private final ICategory iCategory;


    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.ok(iCategory.create(createCategoryRequest));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(iCategory.findAll());
    }
}

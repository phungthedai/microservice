package org.example.dtn2509productservice.service;


import org.example.dtn2509productservice.dto.request.CreateCategoryRequest;
import org.example.dtn2509productservice.dto.response.CategoryResponse;
import org.example.dtn2509productservice.entity.CategoryEntity;

import java.util.List;

public interface ICategory {
    CategoryResponse create(CreateCategoryRequest createCategoryRequest);
    List<CategoryResponse> findAll();
}

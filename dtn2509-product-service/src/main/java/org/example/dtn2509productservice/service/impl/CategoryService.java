package org.example.dtn2509productservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtn2509productservice.dto.request.CreateCategoryRequest;
import org.example.dtn2509productservice.dto.response.CategoryResponse;
import org.example.dtn2509productservice.entity.CategoryEntity;
import org.example.dtn2509productservice.exception.ApplicationErrors;
import org.example.dtn2509productservice.mapper.CategoryMapper;
import org.example.dtn2509productservice.repository.CategoryRepository;
import org.example.dtn2509productservice.service.ICategory;
import org.example.dtn2509productservice.service.IProducts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategory {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        if (createCategoryRequest.getParentId() != null) {
            var patentCategory = categoryRepository.findById(createCategoryRequest.getParentId());
            if (patentCategory.isEmpty()) {
                throw ApplicationErrors.PARENT_CATEGORY_NOT_FOUND();
            }
        }
        CategoryEntity newCategory = categoryRepository.save(categoryMapper.create(createCategoryRequest));
        newCategory.setIsDeleted(false);

        return categoryMapper.to(newCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> findAll() {
        return categoryMapper.List(categoryRepository.findAll());
    }

}

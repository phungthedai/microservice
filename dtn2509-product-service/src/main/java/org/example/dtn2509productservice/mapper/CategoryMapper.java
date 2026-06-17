package org.example.dtn2509productservice.mapper;

import org.example.dtn2509productservice.dto.request.CreateCategoryRequest;
import org.example.dtn2509productservice.dto.response.CategoryResponse;
import org.example.dtn2509productservice.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryEntity create(CreateCategoryRequest createCategoryRequest);
    CategoryResponse to(CategoryEntity categoryEntity);
    List<CategoryResponse> List(List<CategoryEntity> categoryEntity);
}

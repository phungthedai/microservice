package org.example.dtn2509productservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtn2509productservice.dto.request.CreateProductRequest;
import org.example.dtn2509productservice.dto.request.ProductFilter;
import org.example.dtn2509productservice.dto.response.ProductResponse;
import org.example.dtn2509productservice.entity.ProductEntity;
import org.example.dtn2509productservice.exception.ApplicationErrors;
import org.example.dtn2509productservice.mapper.ProductMapper;
import org.example.dtn2509productservice.repository.CategoryRepository;
import org.example.dtn2509productservice.repository.ProductRepository;
import org.example.dtn2509productservice.service.IProducts;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProducts {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse create(CreateProductRequest createProductRequest) {
        var categoryProduct = categoryRepository.findById(createProductRequest.getCategoryId());
        if (categoryProduct.isEmpty()) {
           throw ApplicationErrors.CATEGORY_NOT_FOUND();
        }

        ProductEntity createProduct = productMapper.create(createProductRequest);
        createProduct.setIsDeleted(false);
        ProductEntity newProduct = productRepository.save(createProduct);
        return productMapper.to(newProduct);
    }

    @Override
    public List<ProductResponse> findAll() {
        return productMapper.List(productRepository.findAll());
    }

    @Override
    public List<ProductResponse> search(ProductFilter productFilter) {
        return productRepository.findByIdIn(productFilter.getIds())
                .stream()
                .map(productMapper::to)
                .collect(Collectors.toList());
    }
}

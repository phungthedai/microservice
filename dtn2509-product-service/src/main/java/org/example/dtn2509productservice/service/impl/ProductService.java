package org.example.dtn2509productservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtn2509productservice.dto.request.CreateProductRequest;
import org.example.dtn2509productservice.dto.request.ProductFilter;
import org.example.dtn2509productservice.dto.request.StockProductItemRequest;
import org.example.dtn2509productservice.dto.request.StockProductRequest;
import org.example.dtn2509productservice.dto.response.ProductResponse;
import org.example.dtn2509productservice.entity.ProductEntity;
import org.example.dtn2509productservice.exception.ApplicationErrors;
import org.example.dtn2509productservice.mapper.ProductMapper;
import org.example.dtn2509productservice.repository.CategoryRepository;
import org.example.dtn2509productservice.repository.ProductRepository;
import org.example.dtn2509productservice.service.IProducts;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProducts {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public List<ProductResponse> findAll() {
        return productMapper.List(productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> search(ProductFilter productFilter) {
        return productRepository.findByIdIn(productFilter.getIds())
                .stream()
                .map(productMapper::to)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Boolean stockProduct(StockProductRequest stockProductRequest) {
        List<String> listProductId = new ArrayList<>();
        Map<String, Integer> mapProductId = new HashMap<>();

        for (StockProductItemRequest stockProductItemRequest : stockProductRequest.getStockProductItemRequests()) {
            listProductId.add(stockProductItemRequest.getProductId());
            mapProductId.put(stockProductItemRequest.getProductId(), stockProductItemRequest.getQuantity());
        }

        List<ProductEntity> productEntities = productRepository.findByIdIn(listProductId);

        if (productEntities.isEmpty()) {
            throw ApplicationErrors.PRODUCT_NOT_FOUND();
        }

        for (ProductEntity productEntity : productEntities) {
            Integer newStockQuantity = productEntity.getStock() - mapProductId.get(productEntity.getId());
            if (newStockQuantity < 0) {
                throw new ArithmeticException("Stock quantity overflow");
            }

            productEntity.setStock(newStockQuantity);
        }

        productRepository.saveAll(productEntities);
        return true;
    }
}

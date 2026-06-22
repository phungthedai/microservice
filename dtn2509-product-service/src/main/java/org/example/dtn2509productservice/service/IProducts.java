package org.example.dtn2509productservice.service;

import org.example.dtn2509productservice.dto.request.CreateProductRequest;
import org.example.dtn2509productservice.dto.request.ProductFilter;
import org.example.dtn2509productservice.dto.request.StockProductItemRequest;
import org.example.dtn2509productservice.dto.request.StockProductRequest;
import org.example.dtn2509productservice.dto.response.ProductResponse;

import java.util.List;

public interface IProducts {
    ProductResponse create(CreateProductRequest createProductRequest);
    List<ProductResponse> findAll();
    List<ProductResponse> search(ProductFilter productFilter);
    Boolean stockProduct(StockProductRequest stockProductRequest);
}

package org.example.dtn2509productservice.service;

import org.example.dtn2509productservice.dto.request.CreateProductRequest;
import org.example.dtn2509productservice.dto.response.ProductResponse;

import java.util.List;

public interface IProducts {
    ProductResponse create(CreateProductRequest createProductRequest);
    List<ProductResponse> findAll();
}

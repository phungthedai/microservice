package org.example.dtn2509productservice.mapper;

import org.example.dtn2509productservice.dto.request.CreateProductRequest;
import org.example.dtn2509productservice.dto.response.ProductResponse;
import org.example.dtn2509productservice.entity.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity create(CreateProductRequest createProductRequest);
    ProductResponse to(ProductEntity product);
    List<ProductResponse> List(List<ProductEntity> products);
}

package org.example.dtn2509productservice.repository;

import org.example.dtn2509productservice.dto.response.ProductResponse;
import org.example.dtn2509productservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByIdIn(List<String> ids);
}

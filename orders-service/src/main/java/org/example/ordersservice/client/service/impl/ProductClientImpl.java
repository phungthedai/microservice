package org.example.ordersservice.client.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.response.ProductResponse;
import org.example.ordersservice.client.service.IProductClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClientImpl implements IProductClient {

    private final WebClient webClient;
    @Override
    public List<ProductResponse> search(ProductFilter productFilter) {
        return webClient
                .post()
                .uri("http://localhost:8081/products/search")
                .bodyValue(productFilter)
                .retrieve()
                .bodyToFlux(ProductResponse.class)
                .collectList()
                .block();
    }
}

package org.example.ordersservice.client.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.response.ProductResponse;
import org.example.ordersservice.client.service.IProductClient;
import org.example.ordersservice.exception.ApplicationException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClientImpl implements IProductClient {


    private final WebClient.Builder webClientBuilder;
    @Override
    public List<ProductResponse> search(ProductFilter productFilter) {
        List<ProductResponse> responses =  webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/v1/products/search")
                .bodyValue(productFilter)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ProductResponse>>() {})
                .block();

        if (responses == null) {
            throw new ApplicationException(400, 400, "Call product failed");
        }

        return responses;
    }
}

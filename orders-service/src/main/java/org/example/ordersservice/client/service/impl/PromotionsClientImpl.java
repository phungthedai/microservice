package org.example.ordersservice.client.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.request.PromotionsFindId;
import org.example.ordersservice.client.dto.response.PromotionsResponse;
import org.example.ordersservice.client.service.PromotionsClient;
import org.example.ordersservice.exception.ApplicationException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionsClientImpl  implements PromotionsClient {

    private final WebClient.Builder webClientBuilder;
    @Override
    public PromotionsResponse findId(PromotionsFindId promotionsFindId) {
        try {
            PromotionsResponse promotionsResponse =  webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8083/v1/search-by-id")
                    .bodyValue(promotionsFindId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<PromotionsResponse>() {
                    })
                    .block();
            if (promotionsResponse == null) {
                throw new ApplicationException(40301, 403, "promotions not found");
            }

            return promotionsResponse;
        } catch (Exception e) {
            throw new ApplicationException(null, null, e.getMessage());
        }
    }

    @Override
    public Integer incrementUsedCount(String promotionsId) {
        try {
            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8083/v1/promotions/used-count")
                    .bodyValue(promotionsId)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Integer>() {
                    })
                    .block();
        } catch (Exception e) {
            throw new ApplicationException(null, null, e.getMessage());
        }
    }
}

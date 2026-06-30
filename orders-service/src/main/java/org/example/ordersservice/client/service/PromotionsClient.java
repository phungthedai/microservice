package org.example.ordersservice.client.service;

import org.example.ordersservice.client.dto.request.PromotionsFindId;
import org.example.ordersservice.client.dto.response.PromotionsResponse;

public interface PromotionsClient {
    PromotionsResponse findId(PromotionsFindId promotionsFindId);
    void incrementUsedCount(String promotionsId);
}

package org.example.ordersservice.client.service;

import org.example.ordersservice.client.dto.request.PromotionsFindId;
import org.example.ordersservice.client.dto.response.PromotionsResponse;

import java.util.List;

public interface PromotionsClient {
    PromotionsResponse findId(PromotionsFindId promotionsFindId);
    Integer incrementUsedCount(String promotionsId);
}

package org.example.promotionservice.service;


import org.example.promotionservice.dto.request.PromotionsCreateRequest;
import org.example.promotionservice.dto.request.PromotionsFindId;
import org.example.promotionservice.dto.response.PromotionsResponse;
import org.example.promotionservice.entity.PromotionsEntity;

import java.util.List;


public interface PromotionService {
    PromotionsResponse create(PromotionsCreateRequest promotionsCreateRequest);
    List<PromotionsResponse> findAll();
    PromotionsResponse searchById(PromotionsFindId promotionsFindId);
    Integer incrementUsedCount(String id);
}

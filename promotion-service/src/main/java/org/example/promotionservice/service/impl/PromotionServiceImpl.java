package org.example.promotionservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.promotionservice.dto.request.PromotionsCreateRequest;
import org.example.promotionservice.dto.response.PromotionsResponse;
import org.example.promotionservice.entity.PromotionsEntity;
import org.example.promotionservice.exception.ApplicationErrors;
import org.example.promotionservice.mapper.PromotionMapper;
import org.example.promotionservice.repository.PromotionRepository;
import org.example.promotionservice.service.PromotionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;


    @Override
    public PromotionsResponse create(PromotionsCreateRequest createRequest) {
        Optional<PromotionsEntity> findPromotion = promotionRepository.findById(createRequest.getCode());
        if (findPromotion.isPresent()) {
            throw ApplicationErrors.PROMOTIONS_EXISTS();
        }
        PromotionsEntity promotionsCreate = promotionMapper.create(createRequest);
        return promotionMapper.to(promotionRepository.save(promotionsCreate));
    }

    @Override
    public List<PromotionsResponse> findAll() {
        return promotionMapper.List(promotionRepository.findAll());
    }

    @Override
    public PromotionsResponse searchById(String id) {
        return promotionMapper.to(promotionRepository.findById(id).get());
    }

    @Override
    public Integer incrementUsedCount(String id) {
        return promotionRepository.incrementUsedCount(id);
    }

}

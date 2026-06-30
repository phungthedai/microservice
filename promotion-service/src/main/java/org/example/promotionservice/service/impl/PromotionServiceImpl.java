package org.example.promotionservice.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.promotionservice.dto.request.PromotionsCreateRequest;
import org.example.promotionservice.dto.request.PromotionsFindId;
import org.example.promotionservice.dto.response.PromotionsResponse;
import org.example.promotionservice.entity.PromotionsEntity;
import org.example.promotionservice.exception.ApplicationErrors;
import org.example.promotionservice.exception.ApplicationException;
import org.example.promotionservice.mapper.PromotionMapper;
import org.example.promotionservice.repository.PromotionRepository;
import org.example.promotionservice.service.PromotionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;


    @Override
    @Transactional
    public PromotionsResponse create(PromotionsCreateRequest createRequest) {
        Optional<PromotionsEntity> findPromotion = promotionRepository.findById(createRequest.getCode());
        if (findPromotion.isPresent()) {
            throw ApplicationErrors.PROMOTIONS_EXISTS();
        }
        PromotionsEntity promotionsCreate = promotionMapper.create(createRequest);
        return promotionMapper.to(promotionRepository.save(promotionsCreate));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromotionsResponse> findAll() {
        return promotionMapper.List(promotionRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public PromotionsResponse searchById(PromotionsFindId findId) {
        return promotionMapper.to(promotionRepository.findById(findId.getId()).get());
    }



    @Override
    @Transactional
    public Integer incrementUsedCount(String id) {
//        Optional<PromotionsEntity> promotion = promotionRepository.findById(id);
//        if (promotion.isEmpty()) {
//            throw ApplicationErrors.PROMOTIONS_EXISTS();
//        }
//        promotion.get().setUsedCount(promotion.get().getUsedCount() + 1);
//        PromotionsEntity newPromotion = promotionRepository.save(promotion.get());
//        return newPromotion.getUsedCount();

        log.info("incrementUsedCount");
        throw new ApplicationException("mock");

//        PromotionsEntity promotion = promotionRepository.findById(id)
//                .orElseThrow(() -> ApplicationErrors.PROMOTIONS_EXISTS());
//        promotion.setUsedCount(promotion.getUsedCount() + 1);
//        return promotion.getUsedCount();
    }

}

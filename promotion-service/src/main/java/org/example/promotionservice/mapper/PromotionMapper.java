package org.example.promotionservice.mapper;

import org.example.promotionservice.dto.request.PromotionsCreateRequest;
import org.example.promotionservice.dto.response.PromotionsResponse;
import org.example.promotionservice.entity.PromotionsEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    PromotionsEntity create(PromotionsCreateRequest promotionsCreateRequest);
    PromotionsResponse to(PromotionsEntity promotionsEntity);
    List<PromotionsResponse> List(List<PromotionsEntity> promotionsEntity);
}

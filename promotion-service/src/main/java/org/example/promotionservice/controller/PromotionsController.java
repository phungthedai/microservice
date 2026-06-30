package org.example.promotionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.promotionservice.dto.request.PromotionsCreateRequest;
import org.example.promotionservice.dto.request.PromotionsFindId;
import org.example.promotionservice.dto.response.PromotionsResponse;
import org.example.promotionservice.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("v1/promotions")
public class PromotionsController {

    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PromotionsResponse> create(@RequestBody @Valid PromotionsCreateRequest promotionsCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(promotionService.create(promotionsCreateRequest));
    }

    @PostMapping("search-by-id")
    public ResponseEntity<PromotionsResponse> searchById(@RequestBody @Valid PromotionsFindId promotionsFindId) {
        return ResponseEntity.ok(promotionService.searchById(promotionsFindId));
    }

    @PutMapping("used-count")
    public ResponseEntity<Integer> incrementUsedCount(@RequestBody String id) {
        return ResponseEntity.ok(promotionService.incrementUsedCount(id));
    }
}

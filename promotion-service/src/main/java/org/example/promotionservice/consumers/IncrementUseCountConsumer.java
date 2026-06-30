package org.example.promotionservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.promotionservice.dto.request.PromotionRequestKafka;
import org.example.promotionservice.service.PromotionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class IncrementUseCountConsumer {
    private final PromotionService promotionService;
    private final ObjectMapper objectMapper;

    @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 2000, multiplier = 2))
    @KafkaListener(topics = "increment_use_count")
    public void incrementUsedCountConsumer(String id) throws JsonProcessingException {
        PromotionRequestKafka promotionRequestKafka = objectMapper.readValue(id, PromotionRequestKafka.class);
        promotionService.incrementUsedCount(promotionRequestKafka.getId());
    }
}

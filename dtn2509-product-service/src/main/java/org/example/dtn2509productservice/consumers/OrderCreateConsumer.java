package org.example.dtn2509productservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dtn2509productservice.consumers.dto.OrderCreateEvent;
import org.example.dtn2509productservice.dto.request.StockProductItemRequest;
import org.example.dtn2509productservice.dto.request.StockProductRequest;
import org.example.dtn2509productservice.service.impl.ProductService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreateConsumer {
    private final ProductService productService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RetryableTopic(attempts = "4", backoff = @Backoff(delay = 2000, multiplier = 2))
    @KafkaListener(topics = "order_create")
    public void handelOrderCreateConsumer(String order) throws JsonProcessingException {
        OrderCreateEvent orderCreateEvent = objectMapper.readValue(order, OrderCreateEvent.class);
        List<StockProductItemRequest> stockProductItemRequestList = new ArrayList<>();


        orderCreateEvent.getOrderItemList().forEach(orderItem -> {
            StockProductItemRequest stockProductItemRequest = new StockProductItemRequest();
            stockProductItemRequest.setProductId(orderItem.getProductId());
            stockProductItemRequest.setQuantity(orderItem.getQuantity());

            stockProductItemRequestList.add(stockProductItemRequest);
        });

        StockProductRequest stockProductRequest = new StockProductRequest();
        stockProductRequest.setStockProductItemRequests(stockProductItemRequestList);

        productService.stockProduct(stockProductRequest);
    }

}

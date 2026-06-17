package org.example.ordersservice.common;

import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.response.ProductResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}

package org.example.ordersservice.client.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionsResponse {
    private String id;
    private String code;
    private String name;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal maxDiscountAmount;
    private BigDecimal minOrderAmount = BigDecimal.ZERO;
    private Integer usageLimit;
    private Integer usedCount;
    private Instant startAt;
    private Instant endAt;
    private Boolean isActive;
}

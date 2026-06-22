package org.example.promotionservice.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionsCreateRequest {

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String discountType;

    @NotEmpty
    private BigDecimal discountValue;

    @NotEmpty
    private BigDecimal maxDiscountAmount;

    @NotEmpty
    private BigDecimal minOrderAmount = BigDecimal.ZERO;

    @NotEmpty
    private Integer usageLimit;

    @NotEmpty
    private Instant startAt;

    @NotEmpty
    private Instant endAt;

    @NotEmpty
    private Boolean isActive = true;
}

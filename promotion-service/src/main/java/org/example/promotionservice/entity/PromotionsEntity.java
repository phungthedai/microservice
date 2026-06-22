package org.example.promotionservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "promotions")
public class PromotionsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "discount_type")
    private String discountType;

    @Column(name = "discount_value", precision = 15, scale = 4)
    private BigDecimal discountValue;

    @Column(name = "max_discount_amount", precision = 15, scale = 4)
    private BigDecimal maxDiscountAmount;

    @Column(name = "min_order_amount", precision = 15, scale = 4)
    private BigDecimal minOrderAmount = BigDecimal.ZERO;

    @Column(name = "usage_limit")
    private Integer usageLimit;

    @Column(name = "used_count")
    private Integer usedCount = 0;

    @Column(name = "start_at")
    private Instant startAt;

    @Column(name = "end_at")
    private Instant endAt;

    @Column(name = "is_active")
    private Boolean isActive = true;
}

package org.example.ordersservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private String id;
    private String orderId;
    private String productId;
    private Integer price;
    private Integer quantity;
    private Boolean isDeleted;
    private Instant createdDate;
    private String createdBy;
    private Instant lastModifiedDate;
    private String lastModifiedBy;
}

package org.example.ordersservice.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCreateRequest {

    @NotEmpty
    private String customerId;

    private String promotionId;

    @NotEmpty
    private List<OrdersCreateItemRequest> orderItems;
}

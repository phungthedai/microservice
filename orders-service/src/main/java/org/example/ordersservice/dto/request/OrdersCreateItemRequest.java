package org.example.ordersservice.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCreateItemRequest {

    @NotEmpty
    private String orderId;

    @NotEmpty
    private String productId;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    @Positive
    private Integer quantity;
}

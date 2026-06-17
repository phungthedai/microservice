package org.example.ordersservice.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCreateRequest {

    @NotEmpty
    private String customerId;

    @NotEmpty
    private String status;

    @NotNull
    private Integer totalAmount;
}

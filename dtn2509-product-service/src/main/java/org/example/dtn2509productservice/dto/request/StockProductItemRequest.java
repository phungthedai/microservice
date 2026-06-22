package org.example.dtn2509productservice.dto.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockProductItemRequest {
    @NotEmpty
    private String ProductId;

    @NotEmpty
    private Integer quantity;
}

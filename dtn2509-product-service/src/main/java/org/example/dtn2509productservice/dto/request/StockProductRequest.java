package org.example.dtn2509productservice.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockProductRequest {

    @NotEmpty
    List<StockProductItemRequest> stockProductItemRequests;
}

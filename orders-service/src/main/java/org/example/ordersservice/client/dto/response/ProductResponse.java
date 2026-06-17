package org.example.ordersservice.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String id;
    private String name;
    private Integer price;
    private Integer stock;
    private String categoryId;
    private Boolean isDeleted;
    private Instant createdDate;
    private String createdBy;
    private Instant lastModifiedDate;
    private String lastModifiedBy;
}

package org.example.ordersservice.client.service;

import org.example.ordersservice.client.dto.request.ProductFilter;
import org.example.ordersservice.client.dto.request.StockProductRequest;
import org.example.ordersservice.client.dto.response.ProductResponse;

import java.util.List;

public interface IProductClient {
    List<ProductResponse> search(ProductFilter productFilter);
    Boolean stockProduct (StockProductRequest stockProductRequest);
}

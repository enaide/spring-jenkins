package com.ecfcode.ecomerce.application.request;

import com.ecfcode.ecomerce.domain.entities.OrderLine;
import com.ecfcode.ecomerce.domain.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record AddOrderLineRequest(@JsonProperty("product") Product product, @JsonProperty("quantity") int quantity) {
    public AddOrderLineRequest {
        Objects.requireNonNull(product);
    }
}

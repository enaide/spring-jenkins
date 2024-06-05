package com.ecfcode.ecomerce.application.request;

import com.ecfcode.ecomerce.application.response.CreateOrderResponse;
import com.ecfcode.ecomerce.domain.entities.OrderLine;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public record CreateOrderRequest(@JsonProperty("orderLine") OrderLine orderLine) {
    public CreateOrderRequest{
        Objects.requireNonNull(orderLine);
    }
}

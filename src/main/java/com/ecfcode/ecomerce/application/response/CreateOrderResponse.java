package com.ecfcode.ecomerce.application.response;

import com.ecfcode.ecomerce.domain.entities.OrderLine;
import com.ecfcode.ecomerce.domain.entities.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.java.Log;

import java.util.Objects;

public record CreateOrderResponse(Log orderId) {

}

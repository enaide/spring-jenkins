package com.ecfcode.ecomerce.domain.services.abstracts;

import com.ecfcode.ecomerce.domain.entities.Order;

public interface DiscountPolicy {
    double discount(Order order);
}

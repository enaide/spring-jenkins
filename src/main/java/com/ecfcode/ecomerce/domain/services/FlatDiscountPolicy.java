package com.ecfcode.ecomerce.domain.services;

import com.ecfcode.ecomerce.domain.entities.Order;
import com.ecfcode.ecomerce.domain.services.abstracts.DiscountPolicy;

public class FlatDiscountPolicy implements DiscountPolicy {
    @Override
    public double discount(Order order) {
        return 0.01;
    }
}

package com.ecfcode.ecomerce.domain.services;

import com.ecfcode.ecomerce.domain.entities.Order;
import com.ecfcode.ecomerce.domain.entities.OrderLine;
import com.ecfcode.ecomerce.domain.services.abstracts.SpecialDiscountPolicy;

import java.math.BigDecimal;
import java.util.List;

public class OrderDiscountPolicy extends Order {
    public OrderDiscountPolicy(List<OrderLine> orderLines) {
        super(orderLines);
    }

    public BigDecimal totalCost(SpecialDiscountPolicy discountPolicy) {
        return totalCost().multiply(BigDecimal.valueOf(1 - applyDiscountPolicy(discountPolicy)));
    }

    @Override
    protected double applyDiscountPolicy(SpecialDiscountPolicy discountPolicy) {
        return discountPolicy.discount(this);
    }
}

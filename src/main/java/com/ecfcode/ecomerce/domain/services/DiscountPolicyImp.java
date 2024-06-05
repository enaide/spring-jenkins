package com.ecfcode.ecomerce.domain.services;

import com.ecfcode.ecomerce.domain.entities.Order;
import com.ecfcode.ecomerce.domain.entities.SpecialOrder;
import com.ecfcode.ecomerce.domain.services.abstracts.SpecialDiscountPolicy;

public class DiscountPolicyImp implements SpecialDiscountPolicy {
    @Override
    public double discount(Order order) {
        return 0;
    }

    @Override
    public double discount(SpecialOrder order) {
        if (order.isEligibleForExtraDiscount())
            return 0.20;
        return 0.10;
    }
}

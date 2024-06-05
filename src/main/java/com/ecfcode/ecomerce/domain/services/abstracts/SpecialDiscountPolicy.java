package com.ecfcode.ecomerce.domain.services.abstracts;

import com.ecfcode.ecomerce.domain.entities.SpecialOrder;

public interface SpecialDiscountPolicy extends DiscountPolicy {
    double discount(SpecialOrder order);
}

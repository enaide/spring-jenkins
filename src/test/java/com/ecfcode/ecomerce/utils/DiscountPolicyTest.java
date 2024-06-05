package com.ecfcode.ecomerce.utils;

import com.ecfcode.ecomerce.domain.entities.Order;
import com.ecfcode.ecomerce.domain.services.*;
import com.ecfcode.ecomerce.domain.services.abstracts.DiscountPolicy;
import com.ecfcode.ecomerce.domain.services.abstracts.SpecialDiscountPolicy;
import com.ecfcode.ecomerce.entities.order.OrderFixtureUtils;
import com.ecfcode.ecomerce.domain.entities.OrderLine;
import com.ecfcode.ecomerce.domain.entities.SpecialOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicyTest {
    // @formatter:off
    @DisplayName(
        "given two discount policies, " +
        "when use these policies, " +
        "then single dispatch chooses the implementation based on runtime type"
    )
    // @formatter:on
    @Test
    void test() {
        // given
        Order orderWorth501Dollars = orderWorthNDollars(501);

        DiscountPolicy flatPolicy = new FlatDiscountPolicy();
        DiscountPolicy amountPolicy = new AmountBasedDiscountPolicy();

        // when
        double flatDiscount = flatPolicy.discount(orderWorth501Dollars);
        double amountDiscount = amountPolicy.discount(orderWorth501Dollars);

        // then
        BigDecimal amountToBePaid = orderWorth501Dollars.totalCost().multiply(BigDecimal.valueOf(1 - flatDiscount));
        assertThat(amountToBePaid).isEqualTo(BigDecimal.valueOf(495.99));
        assertThat(flatDiscount).isEqualTo(0.01);
        assertThat(amountDiscount).isEqualTo(0.10);
    }

    @DisplayName(
            "given discount policy accepting special orders, " +
                    "when apply the policy on special order declared as regular order, " +
                    "then regular discount method is used"
    )
    @Test
    void test1() throws Exception {
//        // given
        Order regularOrder = new SpecialOrder(anyOrderLines());
        SpecialOrder specialOrder = new SpecialOrder(anyOrderLines());

        SpecialDiscountPolicy specialPolicy = new SpecialDiscountPolicy() {

            @Override
            public double discount(Order order) {
                return 0.01;
            }

            @Override
            public double discount(SpecialOrder order) {
                return 0.1;
            }
        };


        // when
        double regularDiscount = specialPolicy.discount(regularOrder);
        double specialDiscount = specialPolicy.discount(specialOrder);

        // then
        assertThat(regularDiscount).isEqualTo(0.01);
        assertThat(specialDiscount).isEqualTo(0.1);
    }

    @DisplayName(
        "given special order eligible for extra discount with items worth $100 total, " +
        "when apply 20% discount policy for extra discount orders, " +
        "then cost after discount is $80"
    )
    @Test
    void test2() {
        // given
        boolean eligibleForExtraDiscount = true;
        Order specialOrder = new SpecialOrder(
                OrderFixtureUtils.orderLineItemsWorthNDollars(100),
                eligibleForExtraDiscount
        );
        Order order = new Order(
                OrderFixtureUtils.orderLineItemsWorthNDollars(100)
        );

         SpecialDiscountPolicy discountPolicy = new DiscountPolicyImp();

/*
        SpecialDiscountPolicy discountPolicy = new SpecialDiscountPolicy() {

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
        };
*/

        // when
        BigDecimal totalCostFromSpecialOrder = specialOrder.totalCost(discountPolicy);
        BigDecimal totalCostFromRegularOrder = order.totalCost(discountPolicy);

        // then
        BigDecimal expected = BigDecimal.valueOf(80.00).setScale(2, RoundingMode.HALF_EVEN);
        assertThat(totalCostFromSpecialOrder).isEqualTo(expected);
        BigDecimal expected1 = BigDecimal.valueOf(100.0).setScale(2, RoundingMode.HALF_EVEN);
        assertThat(totalCostFromRegularOrder).isEqualTo(expected1);
    }

    private Order orderWorthNDollars(int totalCost) {
        return new Order(OrderFixtureUtils.orderLineItemsWorthNDollars(totalCost));
    }

    private List<OrderLine> anyOrderLines() {
        return OrderFixtureUtils.anyOrderLines();
    }
}
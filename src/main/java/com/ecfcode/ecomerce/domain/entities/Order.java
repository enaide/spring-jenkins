package com.ecfcode.ecomerce.domain.entities;

import com.ecfcode.ecomerce.domain.exception.DomainException;
import com.ecfcode.ecomerce.domain.services.abstracts.SpecialDiscountPolicy;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Order {

    private Long orderId;
    private final List<OrderLine> orderLines;
    private OrderStatus status;
    private BigDecimal totalCost;

    @JsonCreator
    public Order(@JsonProperty("orderLines") List<OrderLine> orderLines) {
        validateOrderLines(orderLines);
        this.orderLines = new ArrayList<>(orderLines);
        status = OrderStatus.CREATED;
        totalCost = calculateTotalCost();
    }

    public void complete(){
        validateState();
        this.status = OrderStatus.COMPLETED;
    }

    public void addOrder(final Product product, final int quantity) {
        validateState();
        checkNotNull(product);
        OrderLine ol = new OrderLine(product, quantity);
        orderLines.add(ol);
        totalCost = totalCost.add(ol.cost());
    }

    public void addLineItem(OrderLine orderLine) {
        validateState();
        checkNotNull(orderLine);

        orderLines.add(orderLine);
        totalCost = totalCost.add(orderLine.cost());
    }

    public void removeLineItemByProductId(Long productId) {
        validateState();
        OrderLine orderLine = getOrderLineByProductId(productId);
        orderLines.remove(orderLine);

        totalCost = totalCost.subtract(orderLine.cost());
    }

    public void removeLineItemByOrderLineId(Long orderLineId) {
        validateState();
        OrderLine orderLine = getOrderLineByLineId(orderLineId);
        orderLines.remove(orderLine);

        totalCost = totalCost.subtract(orderLine.cost());
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    private OrderStatus getStatus() {
        return status;
    }

    private OrderLine getOrderLineByLineId(final Long id) {
        // return orderLines.get(line);
        return orderLines.stream()
                .filter(orderItem -> orderItem.getOrderLineId().equals(id))
                .findFirst().orElseThrow(() -> new DomainException("OrderLine with " + id + " doesn't exist."));
    }

    private OrderLine getOrderLineByProductId(final Long id) {
        return orderLines.stream()
                .filter((orderLine -> orderLine.getProduct().getProductId().equals(id)))
                .findFirst().orElseThrow(() -> new DomainException("Product with " + id + " doesn't exist."));
    }

    public BigDecimal totalCost() {
        return totalCost;
    }

    public BigDecimal totalCost(SpecialDiscountPolicy discountPolicy){
        BigDecimal amountToBePaid = totalCost().multiply(BigDecimal.valueOf(1 - applyDiscountPolicy(discountPolicy)));
        return amountToBePaid.setScale(2, RoundingMode.HALF_EVEN);
    }

    protected double applyDiscountPolicy(SpecialDiscountPolicy discountPolicy) {
        return discountPolicy.discount(this);
    }

    private BigDecimal calculateTotalCost() {
        return orderLines.stream()
                .map(OrderLine::cost)
                .reduce(BigDecimal::add)
                .orElseThrow(() -> new DomainException("OrderLine doesn't exist."));

            // .orElseThrow(() -> new DomainException("OrderLine with " + id + " doesn't exist."));
    }

    private static void checkNotNull(Object par) {
        if (par == null) {
            throw new NullPointerException("Product cannot be null");
        }
    }

    private static void validateOrderLines(final List<OrderLine> orderLines) {
        if(orderLines == null || orderLines.isEmpty()) {
            throw new DomainException("Order must have at least one order line item");
        }
    }

    private void validateState() {
        if(OrderStatus.COMPLETED.equals(status)) {
            throw new DomainException("The order is in completed state.");
        }
    }

    public List<OrderLine> getOrderItems() {
        return Collections.unmodifiableList(orderLines);
    }
}

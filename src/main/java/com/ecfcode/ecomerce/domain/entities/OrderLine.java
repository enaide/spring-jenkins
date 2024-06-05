package com.ecfcode.ecomerce.domain.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class OrderLine {
    private Long orderLineId;
    private final Product product;
    private final int quantity;

    @JsonCreator
    public OrderLine(@JsonProperty("product") final Product product, @JsonProperty("quantity") final int quantity) {
        super();
        this.product = product;
        this.quantity = quantity;
    }

    Long getOrderLineId() {
        return orderLineId;
    }

    BigDecimal cost() {
        return product.getPrice()
                .multiply(BigDecimal.valueOf(quantity));
    }

    Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderLine other = (OrderLine) obj;
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
        result = prime * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "OrderLine [product=" + product + ", quantity=" + quantity + "]";
    }
}

package com.ecfcode.ecomerce.domain.entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final Long productId;
    private final BigDecimal price;

    @JsonCreator
    public Product(@JsonProperty("id") final Long productId, @JsonProperty("price") final BigDecimal price) {
        this.productId = productId;
        this.price = price;
    }

    BigDecimal getPrice() {
        return price;
    }

    Long getProductId() {
        return productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", productId=" + productId +
                '}';
    }
}

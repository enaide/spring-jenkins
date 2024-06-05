package com.ecfcode.ecomerce.domain.services.abstracts;

import com.ecfcode.ecomerce.domain.entities.OrderLine;
import com.ecfcode.ecomerce.domain.entities.Product;

import java.util.List;

public interface OrderService {
    Long createOrder(List<OrderLine> orderLine);

    void addProduct(Long id, Product product);

    void deleteProduct(Long id, Long productId);

    void addOrderLine(Long id, OrderLine orderLine);

    void deleteOrderLine(Long id, Long orderLine);

    void completeOrder(Long id);
}

package com.ecfcode.ecomerce.domain.repository;

import com.ecfcode.ecomerce.domain.entities.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Optional<Order> findById(Long id);

    Order save(Order order);
}

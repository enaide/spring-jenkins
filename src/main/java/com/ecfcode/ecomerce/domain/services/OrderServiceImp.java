package com.ecfcode.ecomerce.domain.services;

import com.ecfcode.ecomerce.domain.entities.Order;
import com.ecfcode.ecomerce.domain.entities.OrderLine;
import com.ecfcode.ecomerce.domain.entities.Product;
import com.ecfcode.ecomerce.domain.exception.DomainException;
import com.ecfcode.ecomerce.domain.repository.OrderRepository;
import com.ecfcode.ecomerce.domain.services.abstracts.OrderService;

import java.util.List;

public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImp(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long createOrder(List<OrderLine> orderLine) {
        Order order = new Order(orderLine);

        return orderRepository.save(order).getOrderId();
    }

    @Override
    public void addProduct(Long id, Product product) {
        final Order order = getOrder(id);
        order.addOrder(product,1);

        orderRepository.save(order);
    }

    @Override
    public void addOrderLine(Long id, OrderLine orderLine) {
        final Order order = getOrder(id);
        order.addLineItem(orderLine);

        orderRepository.save(order);
    }

    @Override
    public void deleteProduct(Long id, Long productId) {
        final Order order = getOrder(id);
        order.removeLineItemByProductId(productId);

        orderRepository.save(order);
    }

    @Override
    public void deleteOrderLine(Long id, Long orderLine) {
        final Order order = getOrder(id);
        order.removeLineItemByOrderLineId(orderLine);

        orderRepository.save(order);
    }

    @Override
    public void completeOrder(Long id) {
        final Order order = getOrder(id);
        order.complete();

        orderRepository.save(order);
    }

    private Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new DomainException("Order with given id doesn't exist"));
    }
}
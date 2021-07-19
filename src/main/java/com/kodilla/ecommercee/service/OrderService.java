package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> ordersList (){
        return orderRepository.findAll();
    }

    public Optional<Order> findOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }

    public void deleteOrderById(Long orderId){
        orderRepository.deleteById(orderId);
    }
}

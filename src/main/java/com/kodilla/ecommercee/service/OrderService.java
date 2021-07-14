package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;

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

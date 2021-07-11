package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;


    public Order mapOrderToOrderDto(OrderDto orderDto){

        return new Order(
                orderDto.getId(),
                orderDto.getOrderDate(),
                orderDto.getOrderNumber(),
                orderDto.getOrderTotalPrice(),
                orderDto.isOrderIsCompleted(),
                orderDto.isOrderIsSend(),
                orderDto.isOrderIsSend(),
                orderDto.getAddressCountry(),
                orderDto.getAddressCity(),
                orderDto.getAddressPost(),
                orderDto.getAddressStreet(),
                orderDto.getAddressBuildNumber()
        );
    }

    public OrderDto mapOrderToOrderDto(Order order){

        return new OrderDto(order.getId(),
                order.getUser().getId(),
                order.getCart().getId(),
                order.getOrderDate(),
                order.getOrderNumber(),
                order.getOrderTotalPrice(),
                order.isOrderIsCompleted(),
                order.isOrderIsPaid(),
                order.isOrderIsSend(),
                order.getAddressCountry(),
                order.getAddressCity(),
                order.getAddressPost(),
                order.getAddressStreet(),
                order.getAddressBuildNumber()) ;
    }

    public List<OrderDto> mapUserListToDtoList( List<Order> orderList){
        return orderList.stream()
                .map(this::mapOrderToOrderDto)
                .collect(Collectors.toList());
    }

}

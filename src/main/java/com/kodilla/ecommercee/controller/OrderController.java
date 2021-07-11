package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderDto createOrder(@RequestBody OrderDto orderDto){
        orderService.saveOrder(orderMapper.mapOrderToOrderDto(orderDto));
        return  null;
    }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder( @RequestParam OrderDto orderDto) throws OrderNotFoundException{
        if (orderService.findOrderById(orderDto.getId()).isPresent()){
            Order returnOrder =  orderService.saveOrder(orderMapper.mapOrderToOrderDto(orderDto));
            return orderMapper.mapOrderToOrderDto(returnOrder) ;
        }
        throw new  OrderNotFoundException();
    }

    @GetMapping(value = "getOrder")
    public OrderDto getOrder(@RequestParam long orderId) throws OrderNotFoundException{
        Order order = orderService.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
        return orderMapper.mapOrderToOrderDto(order);
    }

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders(){
        List<Order> orderDtoList  = orderService.ordersList();
        return orderMapper.mapUserListToDtoList(orderDtoList) ;
    }

    @DeleteMapping(value = "deleteOrder")
    public boolean deleteOrder(@RequestParam long orderId)throws OrderNotFoundException{

        if (orderService.findOrderById(orderId).isPresent()){
            orderService.deleteOrderById(orderId);
            return true;
        }
        throw new OrderNotFoundException() ;
    }
}
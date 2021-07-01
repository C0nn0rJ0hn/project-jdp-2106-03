package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(@RequestBody OrderDto orderDto){ return  orderDto; }

    @PutMapping(value = "updateOrder")
    public OrderDto updateOrder( @RequestBody OrderDto orderDto) throws OrderNotFoundException{

        if (orderDto.getId() < 5L){return orderDto; }
        throw new OrderNotFoundException();
    }

    @GetMapping(value = "getOrder")
    public OrderDto getOrder(@RequestParam long orderId) throws OrderNotFoundException{

        if (orderId ==1L){
            return new OrderDto(1L, 1L,
                    1L,
                    LocalDate.of(2021, 5, 5),
                    "FV/2020-05-05/001",
                    new BigDecimal(15.55),
                    false,
                    false,
                    false,
                    "Poland",
                    "Rzeszów",
                    "08-555",
                    "Morska",
                    "90210/58"
            );
        }
        throw new OrderNotFoundException();
    }

    @GetMapping(value = "getOrders")
    public List<OrderDto> getOrders(){

        List<OrderDto> returnResult = new ArrayList<>();

        returnResult.add(new OrderDto(1L, 1L,
                1L,
                LocalDate.of(2021, 5, 5),
                "FV/2020-05-05/001",
                new BigDecimal(15.55),
                false,
                false,
                false,
                "Poland",
                "Rzeszów",
                "08-555",
                "Morska",
                "90210/58"
                ));
        returnResult.add(new OrderDto(2L, 1L,
                2L,
                LocalDate.of(2021, 4, 22),
                "FV/2020-04-22/001",
                new BigDecimal(1_377.12),
                false,
                false,
                false,
                "Poland",
                "Kraków",
                "00-478",
                "Słoneczna",
                "90/58"
        ));
        returnResult.add(new OrderDto(3L, 2L,
                3L,
                LocalDate.of(2021, 7, 10),
                "FV/2020-07-10/001",
                new BigDecimal(3_356015),
                false,
                false,
                false,
                "Poland",
                "Olsztyn",
                "40-0558",
                "Szczupakowa",
                "100"
        ));
        returnResult.add(new OrderDto(4L, 3L,
                4L,
                LocalDate.of(2021, 6, 11),
                "FV/2020-06-11/501",
                new BigDecimal(85.05),
                false,
                false,
                false,
                "Poland",
                "Żelazowa Wola",
                "88-858",
                "Muzyczna",
                "15/65"
        ));

        return returnResult;
    }

    @DeleteMapping(value = "deleteOrder")
    public boolean deleteOrder(@RequestParam long orderId){
        if ( orderId < 5L){
            return true;
        }
        return false;
    }
}

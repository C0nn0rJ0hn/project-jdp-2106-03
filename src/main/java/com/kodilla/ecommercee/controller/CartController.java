package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam long cartId) throws CartNotFoundExeption {

//    fixed data  - return object for test purpose only
        CartDto cartDto2 = new CartDto(1, new BigDecimal(55.3), false);
        CartDto cartDto3 = new CartDto(1, new BigDecimal(55.3), false);

        if (cartId == 1L){ return new CartDto(1, new BigDecimal(55.3), false); }
        if (cartId == 2L){ return new CartDto(2, new BigDecimal(185.35), false); }
        if (cartId == 3L){ return new CartDto(3, new BigDecimal(333.00), false); }
        if (cartId == 4L){ return new CartDto(4, new BigDecimal(2_499.99), false); }

        throw new CartNotFoundExeption();
    }

    @DeleteMapping(value = "deleteCart")
    public boolean deleteCart(@RequestParam long cartId){

        if (cartId <= 4L){ return true; }

        return false;
    }

    @PutMapping(value = "updateCart")
    public CartDto updateCart(@RequestBody CartDto cartDto) throws CartNotFoundExeption {

        if (cartDto.getCartId() <= 4L){ return cartDto; }

        throw new CartNotFoundExeption();
    }

    @PostMapping(value = "createCart")
    public CartDto createCart(@RequestBody CartDto cartDto){
        return cartDto;
    }
}

package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.controller.exception.CardNotFoundException;
import com.kodilla.ecommercee.domain.CartDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @GetMapping(value = "getCart")
    public CartDto getCard(@RequestParam long cardId) throws CardNotFoundException {

//    fixed data  - return object for test purpose only
        CartDto cartDto2 = new CartDto(1, new BigDecimal(55.3), false);
        CartDto cartDto3 = new CartDto(1, new BigDecimal(55.3), false);

        if (cardId == 1L){ return new CartDto(1, new BigDecimal(55.3), false); }
        if (cardId == 2L){ return new CartDto(2, new BigDecimal(185.35), false); }
        if (cardId == 3L){ return new CartDto(3, new BigDecimal(333.00), false); }
        if (cardId == 4L){ return new CartDto(4, new BigDecimal(2_499.99), false); }

        throw new CardNotFoundException();
    }

    @DeleteMapping(value = "deleteCart")
    public boolean deleteCard(@RequestParam long cardId ){

        if (cardId <= 4L){ return true; }

        return false;
    }

    @PutMapping(value = "updateCart")
    public CartDto updateCard(@RequestBody CartDto cartDto) throws CardNotFoundException {

        if (cartDto.getCardId() <= 4L){ return cartDto; }

        throw new CardNotFoundException();
    }

    @PostMapping(value = "createCart")
    public CartDto createCard(@RequestBody CartDto cartDto){
        return cartDto;
    }
}

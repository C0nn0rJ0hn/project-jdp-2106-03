package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.ProductCondition;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "cartContains")
    public List<ProductDto> cartContains(@RequestParam long cartId){

        List<ProductDto> cartContains = new ArrayList<>();
        if(cartId < 10 ) {
            cartContains.add(new ProductDto(1L, "Xbox", "Xbox shortDesc", "Xbox longDesc longDesc", 6500.00,
                    3, ProductCondition.NEW, 20.00, true, 5L));
            cartContains.add(new ProductDto(2L, "T-shirt", "T-shirt shortDesc", "T-shirt long longDesc", 55.99,
                    3, ProductCondition.NEW, 20.00, true, 6L));
        }

        return cartContains;
    }

    @PutMapping(value = "addProductToCart")
    public List<ProductDto> addProductToCart(@RequestParam long cartId,
                                             @RequestParam long productId){

        List<ProductDto> resultAfterAdd  = cartContains(cartId);

        resultAfterAdd.add(new ProductDto(productId, "Convers", "Convers shortDesc", "Convers long longDesc", 155.99,
                22, ProductCondition.NEW, 2.00, true, 7L));
        return resultAfterAdd;
    }

    @DeleteMapping(value = "removeProdFromCart")
    public List<ProductDto> removeProductFromCart(@RequestParam long cartId,
                                                  @RequestParam long productId){

        List<ProductDto> resultAfterRemoveItems = cartContains(cartId);
        resultAfterRemoveItems.remove((int)productId);
        return resultAfterRemoveItems;
    }

    @PostMapping(value = "createOrder")
    public boolean createOrder(@RequestParam long cartId){
        System.out.println("Cart number: " + cartId + ", save as Order." );
        return true;
    }
}

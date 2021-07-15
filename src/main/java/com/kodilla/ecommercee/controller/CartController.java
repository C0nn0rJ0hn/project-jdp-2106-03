package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exception.CartNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam Long cartId) throws CartNotFoundException {

        Cart cart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);

        return cartMapper.mapToCartDto(cart);
    }

    @DeleteMapping(value = "deleteCart")
    public void deleteCart(@RequestParam Long cartId){
        cartService.deleteCart(cartId);
    }

    @PutMapping(value = "updateCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto updateCart(@RequestBody CartDto cartDto) {

        Cart cart = cartMapper.mapToCart(cartDto);
        Cart savedCart = cartService.saveCart(cart);

        return cartMapper.mapToCartDto(savedCart);
    }

    @PostMapping(value = "createCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto createCart(@RequestBody CartDto cartDto){

        Cart cart = cartService.saveCart(cartMapper.mapToCart(cartDto));

        return cartMapper.mapToCartDto(cart);
    }

    @GetMapping(value = "cartContains")
    public List<ProductDto> cartContains(@RequestParam Long cartId) throws CartNotFoundException {

        cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        List<Product> productsFromCart = cartService.getProductsFromCart(cartId);

        return productMapper.mapToProductDtoList(productsFromCart);
    }

    @PutMapping(value = "addProductToCart")
    public CartDto addProductToCart(@RequestParam Long cartId,
                                    @RequestParam Long productId) throws CartNotFoundException {

        cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        productService.getProductById(productId).get();
        Cart activeCart = cartService.addProductToCart(productId, cartId);

        return cartMapper.mapToCartDto(activeCart);

    }

    @DeleteMapping(value = "removeProductFromCart")
    public CartDto removeProductFromCart(@RequestParam Long cartId,
                                         @RequestParam Long productId) throws CartNotFoundException {

        cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        productService.getProductById(productId).get();
        Cart activeCart = cartService.deleteProductFromCart(productId, cartId);

        return cartMapper.mapToCartDto(activeCart);

    }

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(@RequestParam Long cartId,
                                @RequestParam Long userId) {

        Order orderBasedOnCart = cartService.createOrderBasedOnCart(cartId, userId);
        orderService.saveOrder(orderBasedOnCart);

        return orderMapper.mapOrderToOrderDto(orderBasedOnCart);
    }
}

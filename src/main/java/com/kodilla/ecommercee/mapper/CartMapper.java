package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartMapper {

    @Autowired
    private ProductRepository productRepository;

    public Cart mapToCart(final CartDto cartDto) {

        return new Cart(
                cartDto.getCartId(),
                cartDto.getCartSum(),
                cartDto.isCartClosed(),
                cartDto.getProducts(),
                productRepository.findById(cartDto.getProductId())
        );
    }

    public CartDto mapToCartDto(final Cart cart) {

        return new CartDto(

                cart.getId(),
                cart.getCartSum(),
                cart.isCartClosed(),
                cart.getProducts(),
                cart.getProductId()
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> carts) {

        return carts.stream().map(this::mapToCartDto).collect(Collectors.toList());
    }
}

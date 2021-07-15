package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.controller.exception.UserIsBlockedException;
import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart saveCart(final Cart cart) {

        return cartRepository.save(cart);
    }

    public Optional<Cart> getCart(final Long cartId) {

        return cartRepository.findById(cartId);
    }

    public void deleteCart(final Long cartId) {

        cartRepository.deleteById(cartId);
    }

    public List<Product> getProductsFromCart(final Long cartId) {

        Optional<Cart> cartById = cartRepository.findById(cartId);
        Cart activeCart = cartById.get();

        return activeCart.getProducts();
    }


    public Cart addProductToCart(final Long productId, final Long cartId) {

        Optional<Product> productToAdd = productRepository.findById(productId);
        Optional<Cart> cartById = cartRepository.findById(cartId);
        Product addedProduct = productToAdd.get();
        Cart activeCart = cartById.get();
        addedProduct.getCarts().add(activeCart);
        cartRepository.save(activeCart);

        return activeCart;
    }

    public Cart deleteProductFromCart(final Long productId, final Long cartId) {

        Optional<Product> productToDelete = productRepository.findById(productId);
        Optional<Cart> cartById = cartRepository.findById(cartId);
        Product deletedProduct = productToDelete.get();
        Cart activeCart = cartById.get();
        deletedProduct.getCarts().remove(activeCart);
        cartRepository.save(activeCart);

        return activeCart;
    }

    public Order createOrderBasedOnCart (final Long cartId, final Long userId) throws UserIsBlockedException {

        Optional<Cart> cartById = cartRepository.findById(cartId);
        Cart activeCart = cartById.get();

        Optional<User> userById = userRepository.findById(userId);
        User activeUser = userById.get();
        Order createdOrder = new Order();

        if (!activeUser.isBlocked()) {

            createdOrder.setCart(activeCart);
            createdOrder.setOrderIsCompleted(true);
            createdOrder.setUser(activeUser);

            activeCart.setCartClosed(true);

            Cart newCart = new Cart();
            activeUser.setCart(newCart);

            userRepository.save(activeUser);
            cartRepository.save(activeCart);
            cartRepository.save(newCart);

        } else { throw new UserIsBlockedException();}

        return orderRepository.save(createdOrder);
    }

}

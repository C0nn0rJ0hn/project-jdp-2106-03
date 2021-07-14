package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
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

    public Order createOrderBasedOnCart (final Long cartId) {

        Optional<Cart> cartById = cartRepository.findById(cartId);
        Cart activeCart = cartById.get();
        Order createdOrder = new Order();
        createdOrder.setCart(activeCart);
        createdOrder.setOrderIsCompleted(true);

        return orderRepository.save(createdOrder);
    }

}

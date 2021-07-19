package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.UserIsBlockedException;
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

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
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
        activeCart.getProducts().add(addedProduct);

        BigDecimal currentSumOffCart = BigDecimal.ZERO;
        if (activeCart.getCartSum() != null){ currentSumOffCart = activeCart.getCartSum();  }
        BigDecimal prodPrice = BigDecimal.valueOf(addedProduct.getProductPrice());
        currentSumOffCart = currentSumOffCart.add(prodPrice).round(MathContext.DECIMAL32).setScale(2);
        activeCart.setCartSum(currentSumOffCart);

        addedProduct.getCarts().add(activeCart);
        cartRepository.save(activeCart);

        return activeCart;
    }

    public Cart deleteProductFromCart(final Long productId, final Long cartId) {

        Optional<Product> productToDelete = productRepository.findById(productId);
        Optional<Cart> cartById = cartRepository.findById(cartId);
        Product deletedProduct = productToDelete.get();
        Cart activeCart = cartById.get();

        int i =  activeCart.getProducts().indexOf(deletedProduct);
        activeCart.getProducts().remove(i);

        BigDecimal currSumOfCartPrice = BigDecimal.ZERO;
        if (activeCart.getCartSum() != null){ currSumOfCartPrice = activeCart.getCartSum();  }
        BigDecimal prodPrice = BigDecimal.valueOf(deletedProduct.getProductPrice());
        currSumOfCartPrice = currSumOfCartPrice.subtract(prodPrice).round(MathContext.DECIMAL32).setScale(2);
        activeCart.setCartSum(currSumOfCartPrice);

        cartRepository.save(activeCart);

        return activeCart;
    }

    public Order createOrderBasedOnCart (final Long cartId, final Long userId) throws UserIsBlockedException {

        Optional<Cart> cartById = cartRepository.findById(cartId);
        Cart activeCart = cartById.get();

        Optional<User> userById = userRepository.findById(userId);
        User activeUser = userById.get();
        Order createdOrder = new Order();

        if (!activeUser.isBlocked() && activeCart.getCartSum() != null) {

            createdOrder.setCart(activeCart);
            createdOrder.setUser(activeUser);
            createdOrder.setOrderTotalPrice(activeCart.getCartSum().add(new BigDecimal("9.90")));
            createdOrder.setOrderIsCompleted(false);
            createdOrder.setOrderIsPaid(false);
            createdOrder.setOrderDate(LocalDate.now());
            createdOrder.setOrderNumber("INV/" + LocalDate.now() + "/" +  activeCart.getId());

            activeCart.setCartClosed(true);
            Cart newCart = new Cart();
            activeUser.setCart(cartRepository.save(newCart));

            cartRepository.save(activeCart);
            userRepository.save(activeUser);
            return orderRepository.save(createdOrder);
        }
        throw new UserIsBlockedException("User is blocked or there are no products in cart");
    }
}

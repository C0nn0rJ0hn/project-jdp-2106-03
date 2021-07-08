package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductCondition;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;


    @Autowired
    private OrderRepository orderRepository;


    @Transactional
    @Test
    public void shouldCreateCart() {

        //Given
        Cart cart1 = new Cart(new BigDecimal("0"), false);
        cartRepository.save(cart1);

        //When
        List<Cart> carts = cartRepository.findAll();

        //Then
        assertEquals(2, carts.size());

        //Cleanup
        cartRepository.delete(cart1);
    }


    @Test
    public void shouldPresentsElementsInEmptyCart() {

        //Given
        List<Product> products = new ArrayList<>();
        Cart cart1 = new Cart(1L, new BigDecimal("0"), false, products);

        cartRepository.save(cart1);

        //When
        List<Product> productsInCart = cart1.getProducts();

        //Then
        Assert.assertEquals(productsInCart.size(), products.size());

        //Cleanup
       cartRepository.delete(cart1);

    }

    @Test
    public void shouldAddProductsToCart() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Cart cart = new Cart(new BigDecimal("1000"), false);

        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart);

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);


        //Then
        Assert.assertEquals(2, cart.getProducts().size());

        //Cleanup
        cartRepository.delete(cart);
        productRepository.delete(product1);
        productRepository.delete(product2);

    }

    @Test
    public void shouldPresentsProductsInCart() {

        //Given
        List<Product> products = new ArrayList<>();
        Cart cart1 = new Cart(1L, new BigDecimal("0"), false, products);
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);

        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart1);

        //When
        List<Product> productsInCart = cart1.getProducts();
        productsInCart.add(product1);
        productsInCart.add(product2);

        //Then
        Assert.assertEquals(2, products.size());

        //Cleanup
        cartRepository.delete(cart1);
        productRepository.delete(product1);
        productRepository.delete(product2);

    }


    @Transactional
    @Test
    public void shouldDeleteProductInCart() {

        //Given
        Cart cart1 = new Cart(new BigDecimal("200"), false);
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);

        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart1);

        List<Product> productsInCart = cart1.getProducts();
        productsInCart.add(product1);
        productsInCart.add(product2);


        //When
        productsInCart.remove(product2);
        cartRepository.save(cart1);

        //Then
        Assert.assertEquals(1, productsInCart.size());

        //Cleanup
        cartRepository.delete(cart1);
        productRepository.delete(product1);
        productRepository.delete(product2);
    }


    @Transactional
    @Test
    public void shouldCreateOrderBasedOnCart() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);

        Cart cart1 = new Cart(new BigDecimal("200"), true);

        cart1.getProducts().add(product1);
        cart1.getProducts().add(product2);

        productRepository.save(product1);
        productRepository.save(product2);
        cartRepository.save(cart1);

        Order order = new Order();

        //When
        order.setCart(cart1);
        orderRepository.save(order);
        List<Product> productsInCart = order.getCart().getProducts();


        //Then
        Assert.assertEquals(2, productsInCart.size());

        //Cleanup
        orderRepository.delete(order);
        cartRepository.delete(cart1);
        productRepository.delete(product1);
        productRepository.delete(product2);
    }

}

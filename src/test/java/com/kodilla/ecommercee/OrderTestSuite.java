package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void shouldAddOrder() {

        // Given
        Order order = new Order(
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

        // When
        orderRepository.save(order);
        List<Order> orders = orderRepository.findAll();

        // Then
        Assert.assertEquals(1, orders.size());

        // Clean up
        orderRepository.delete(order);
    }

    @Test
    public void shouldGetAllOrders() {

        // Given
        Order order = new Order(
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
        orderRepository.save(order);
        Order order1 = new Order(
                LocalDate.of(2020, 5, 5),
                "FV/2019-05-05/001",
                new BigDecimal(16.55),
                false,
                false,
                false,
                "Poland",
                "Katowice",
                "07-456",
                "Głębinowa",
                "90455/58"
        );
        orderRepository.save(order1);

        // When
        List<Order> orders = orderRepository.findAll();

        // Then
        Assert.assertEquals(2, orders.size());

        // Clean up
        orderRepository.delete(order);
        orderRepository.delete(order1);
    }

    @Test
    public void shouldFindOrderById() {

        // Given
        Order order = new Order(
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
        orderRepository.save(order);

        // When
        Optional<Order> result = orderRepository.findById(order.getId());

        // Then
        Assert.assertEquals("Poland", result.get().getAddressCountry());

        // Clean up
        orderRepository.delete(order);
    }

    @Test
    public void shouldDeleteOrderById() {

        // Given
        Order order = new Order(
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
        orderRepository.save(order);
        Order order1 = new Order(
                LocalDate.of(2020, 5, 5),
                "FV/2019-05-05/001",
                new BigDecimal(16.55),
                false,
                false,
                false,
                "Poland",
                "Katowice",
                "07-456",
                "Głębinowa",
                "90455/58"
        );
        orderRepository.save(order1);

        // When
        orderRepository.deleteById(order.getId());

        // Then
        Assert.assertEquals(1, orderRepository.findAll().size());

        // Cleanup
        orderRepository.delete(order1);
    }

    @Test
    public void shouldAddOrderToUser() {

        // Given
        Order order = new Order(
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

        User user = new User("Basia", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false);

        //When
        orderRepository.save(order);
        order.setUser(user);
        userRepository.save(user);

        //Then
        Assert.assertEquals("Basia", order.getUser().getName());

        //Cleanup
        orderRepository.delete(order);
        userRepository.delete(user);
    }

    @Transactional
    @Test
    public void shouldCartBePresentWhenOrderIsDeleted() {

        //Given
        Cart cart = new Cart(new BigDecimal("500"), true);
        cartRepository.save(cart);

        Order order = new Order(
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

        order.setCart(cart);
        orderRepository.save(order);
        orderRepository.delete(order);

        //Then
        Assert.assertEquals(1, cartRepository.findAll().size());

        //Cleanup
        cartRepository.delete(cart);
    }
}

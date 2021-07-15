package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTestSuite {

    public User userAdam = new User("Adam", "Test", "som0@mail.cc", "+48 555 55 55", "888-888-88-88", false, null,null);
    public User userBart = new User("Bart", "Test", "som2@mail.cc", "+48 555 55 55", "888-888-88-88", false, null,null);
    public User userPawel = new User("Pawel", "Test", "som1@mail.cc", "+48 555 55 55", "888-888-88-88", false, null,null);

    @Autowired
    public UserRepository userRepository ;

    @Autowired
    public CartRepository cartRepository ;

    @Autowired
    public OrderRepository orderRepository;


    @Test
    public void addPureUsers(){
        //G
        userRepository.save(userAdam);
        userRepository.save(userBart);
        userRepository.save(userPawel);

        //W
        List<User> resultList = userRepository.findAll();

        //T
        System.out.println("Size After Add: " + resultList.size());
        assertEquals(3,resultList.size() );

    }


    @Test
    public void deletePureUser(){

        //G
        userRepository.save(userAdam);
        userRepository.save(userBart);
        userRepository.save(userPawel);

        List<User> resultUserList = userRepository.findAll();
        System.out.println("Size BEFORE delete: " + resultUserList.size());

        //W
        userRepository.deleteById( userBart.getId());
        resultUserList = userRepository.findAll();

        //T
        System.out.println("Size After delete: " + resultUserList.size());
        assertEquals(2, resultUserList.size() );
    }


    @Test
    public void userWithOrderAddAndDeleteOrder(){
        //G

//        User Adam
        Order orderCoffe = new Order(LocalDate.of(2020, 5, 5), "test Cofee", new BigDecimal(55.0), false, false, false, "polan", "Sopot", "558-55", "Morska","55/5");
        Order orderXbox = new Order(LocalDate.of(2020, 5, 5), "test Xbox", new BigDecimal(560), false, false, false, "polan", "Sopot", "558-55", "Morska","55/5");
        List<Order> orderListAdam = new ArrayList<>();
        orderListAdam.add(orderCoffe);
        orderListAdam.add(orderXbox);

        User userAdam = new User("Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false,
                null,
                orderListAdam);
//        orderCoffe.setUser(userAdam);

//        User BART
        Order orderTea = new Order(LocalDate.of(2020, 5, 5), "test Tea", new BigDecimal(57.0), false, false, false, "polan", "Sopot", "558-55", "Morska","55/5");
        List<Order> orderListBart = new ArrayList<>();
        orderListAdam.add(orderTea);


        User userBart = new User("Bart", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false,
                null,
                orderListBart);


        User userPawel = new User("Pawel", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false,
                null,
                null);

        //W

        orderRepository.save(orderTea);
        orderRepository.save(orderCoffe);
        orderRepository.save(orderXbox);

        userRepository.save(userAdam);
        userRepository.save(userBart);
        userRepository.save(userPawel);

        List<User> userList = userRepository.findAll();
        List<Order> orderListInRepository = orderRepository.findAll();

        //T
        System.out.println(" =============== SIZE userList.size(): " + userList.size()  + " ================\n");
        assertEquals(3,userList.size());

        System.out.println(" =============== SIZE orderListInRepository: " + orderListInRepository.size() + " ================\n");
        assertEquals(3,orderListInRepository.size());

        System.out.println("Ordes list after add:");
        for (Order order: orderListInRepository) {
            System.out.println(" ID: " + order.getId() + " - "
                    + order.getOrderTotalPrice() + " - " +
                    order.getOrderNumber());
        }

//      *******************************************************************
//        Remove order for user Adam
//      *******************************************************************
        orderRepository.deleteById(orderXbox.getId());

        userList = userRepository.findAll();
        orderListInRepository = orderRepository.findAll();

        //T
        System.out.println(" =============== SIZE after DELETE  userList.size(): " + userList.size()  + " ================");
        assertEquals(3,userList.size());

        System.out.println(" =============== SIZE after DELETE orderListInRepository: " + orderListInRepository.size() + " ================");
        assertEquals(2,orderListInRepository.size());

        System.out.println("\nOrders list AFTER DELETE: =:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=");
        for (Order order: orderListInRepository) {
            System.out.println("ID: " + order.getId() + " - "
                    + order.getOrderTotalPrice() + " - " +
                    order.getOrderNumber());
        }
    }


    @Test
    public void findUserById(){
        //W
        userRepository.save(userAdam);
        userRepository.save(userBart);
        userRepository.save(userPawel);

        //G
        Optional<User> result = userRepository.findById(userAdam.getId());

        //T
        System.out.println("Adam" + result.get().getName());
        assertEquals("Adam", result.get().getName());

    }

    @Test
    public void userEntityAddUsersWitCartTest(){

        List<Product> productList = new ArrayList<>();

        Cart cartForBart = new Cart(new BigDecimal(144.0), true);
        Cart cartForPablo = new Cart(new BigDecimal(2_214.0), false);

        User userPabl = new User("Pablo", "Test", "som2@mail.cc", "+48 555 55 55", "888-888-88-88", false,
                cartForPablo,
                null);
        userBart.setCart(cartForBart);

        //W
        userRepository.save(userAdam);
        userRepository.save(userBart);
        userRepository.save(userPawel);
        userRepository.save(userPabl);

        cartRepository.save(cartForBart);
        cartRepository.save(cartForPablo);

        List<User> resultListUsers = userRepository.findAll();
        List<Cart> cartList = cartRepository.findAll();

        //T
        System.out.println("just added USER, size is :" + resultListUsers.size() + " ======================= END");
        System.out.println("just added cart, size is :" + cartList.size() + " ======================= END");
        assertEquals(4, resultListUsers.size() );
        assertEquals(2, cartList.size() );

    }

    @Test
    public void deleteCartForUser(){

        //G
        Cart cartForPablo = new Cart(new BigDecimal(2_214.0), false);
        Cart cartForBart = new Cart(new BigDecimal(144.0), true);

        userPawel.setCart(cartForPablo);
        userBart.setCart(cartForBart);

        cartRepository.save(cartForPablo);
        cartRepository.save(cartForBart);

        userRepository.save(userAdam);
        userRepository.save(userBart);
        userRepository.save(userPawel);

        List<User> resultListUsers = userRepository.findAll();
        List<Cart> cartList = cartRepository.findAll();

        System.out.println("\tResultListUsers.size(): " + resultListUsers.size() +
        " | cartList.size(): " +  cartList.size());
        assertEquals(3, resultListUsers.size());
        assertEquals(2, cartList.size());

        //W
        userBart.setCart(null);
        userRepository.save(userBart);
        cartRepository.deleteById(cartForBart.getId());

        resultListUsers = userRepository.findAll();
        cartList = cartRepository.findAll();

        //T

        System.out.println("\tAFTER delete resultListUsers.size(): " + resultListUsers.size() +
                " | cartList.size(): " +  cartList.size());
        assertEquals(3, resultListUsers.size());
        assertEquals(1, cartList.size());
    }
}
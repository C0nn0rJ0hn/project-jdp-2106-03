package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityTestSuite {

    @Autowired
    public UserRepository userRepository ;

    @Autowired
    public CartRepository cartRepository ;

    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public GroupRepository groupRepository;

    @Test
    public void groupsEntityPureGroupsTestSuite(){
//      test for groups without any product
        //G
        Group groupRTV = new Group("RTV groupe");
        Group groupSmallAGD = new Group("Small AGD");

        //W
        groupRepository.save(groupRTV)     ;
        groupRepository.save(groupSmallAGD)     ;

        List<Group> resultList = groupRepository.findAll();

        //T
        assertEquals(2, resultList.size());

        //Clenauo
        groupRepository.deleteAll();

    }

    @Test
    public void gruopsWithListTestSuite(){

        //G
        List<Product> products0 = new ArrayList<>();
        List<Product> products1 = new ArrayList<>();
        Group groupBigRTV = new Group("Big RTV", products0);
        Group groupRTV = new Group("RTV groupe", products1);

        //W
        groupRepository.save(groupBigRTV);
        groupRepository.save(groupRTV);

        List<Group> resultList = groupRepository.findAll();

        //T
        assertEquals(2, resultList.size());
        System.out.println("resultList" + resultList.size());

        //cleanUp
        groupRepository.deleteAll();

    }


    @Test
    public void groupWithListsAndProductsTestSuite(){

        //G
        List<Product> products0 = new ArrayList<>();
        List<Product> products1 = new ArrayList<>();
//        public Product(String name, String shortDesc, String longDesc, double productPrice, int quantityOnStore, ProductCondition productCondition, double productWeight, boolean stillOnSale) {
        Product cofeeMachine = new Product( "Cofee ma", "Shoprt dessc", "longDesc", 33.0, 52, ProductCondition.NEW, 55.0, true);
        Product xBoxProd = new Product( "Xbox", "Shoprt dessc", "longDesc", 33.0, 52, ProductCondition.NEW, 55.0, true);
        Product playStation = new Product( "PlayStation", "Shoprt dessc", "longDesc", 33.0, 52, ProductCondition.NEW, 55.0, true);
        products1.add(cofeeMachine);
        products0.add(xBoxProd);
        products0.add(playStation);
        Group groupBigRTV = new Group("Big RTV", products0);
        Group groupRTV = new Group("RTV groupe", products1);
        cofeeMachine.setGroup(groupRTV);

        //W
        groupRepository.save(groupBigRTV);
        groupRepository.save(groupRTV);

        List<Group> resultList = groupRepository.findAll();
        List<Product> resultListProducts = productRepository.findAll();

        //T
        assertEquals(2, resultList.size());
        assertEquals(3, resultListProducts.size());

        System.out.println("First Add resultList.size(): " + resultList.size());
        System.out.println("First Add resultListProducts.size(): " + resultListProducts.size());

        // check for selection by ID
        resultList.stream()
                .forEach( item-> System.out.println( "ID groups,  is: " +  item.getId() + " - and name: " + item.getName() ));

        Optional<Group> selectedGroup = groupRepository.findById(1L);
        System.out.println("selected group: " + selectedGroup.get().getId()  + " - " +  selectedGroup.get().getName());

        // check for delete by ID
        groupRepository.deleteById(4L);

        resultList = groupRepository.findAll();

        System.out.println("\tNewSize resultList.size(): " + resultList.size());
        System.out.println("\tNEw Size resultListProducts.size(): " + resultListProducts.size());

        assertEquals(1, resultList.size());
        //cleanUp
        groupRepository.deleteAll();
        productRepository.deleteAll();

        //W
    }



    @Test
    public void userEntityTest(){
//        only save users in table  -* without carts or orders
        //G
        User userAdam = new User(1L, "Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false, null,null);
        User userBart = new User(2L, "Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false);

        userRepository.save(userAdam);
        userRepository.save(userBart);

        //W
        List<User> resultList = userRepository.findAll();

        //T
        assertEquals(2,resultList.size() );
        System.out.println(" ======== Result size: " + resultList.size()  + " ============");
    }

    @Test
    public void userWithCartEntityTest(){
//        only save users in table  -* with ONE carts  - no orders
        //G

        User userAdam = new User(1L, "Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false);
        User userBart = new User(2L, "Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false);

        Cart adamCart = new Cart(4L,new BigDecimal(44.0), true);
        Cart bartCart = new Cart(3L,new BigDecimal(44.0), true);

        //W
        userAdam.setCart(adamCart);
        userBart.setCart(bartCart);

        userRepository.save(userAdam);
        userRepository.save(userBart);

        List<User> resultList = userRepository.findAll();
        List<Cart> cartsResult = cartRepository.findAll();

        System.out.println(" ======== Result USER resultList size: " + resultList.size()  + " ============");
        System.out.println(" ======== Result CART cartRepository size: " + cartsResult.size()  + " ============");
        //T
        assertEquals(2,resultList.size() );
        assertEquals(2,cartsResult.size() );
    }

    @Test
    public void userWithOrdersEntityTest(){
//
        //G
        Order orderFirst = new Order(1L, LocalDate.of(2020, 1, 1), "int/02", new BigDecimal(55.0), false, false, false,
                "Polandia", "Sopot", "00-555", "Malszowska", "5525/55 lok. 54");
        Order orderSecond = new Order(2L, LocalDate.of(2020, 1, 1), "int/02", new BigDecimal(2_255.0), true, true, true,
                "Polandia", "Sopot", "00-555", "Malszowska", "5525/55 lok. 54");

        List<Order> orderListOne = new ArrayList<>();
        orderListOne.add(orderFirst);
        orderListOne.add(orderSecond);

        User userAdam = new User(1L, "Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false,
                null,
                orderListOne);

        Order orderforBart = new Order(3L, LocalDate.of(2020, 1, 1), "int/02", new BigDecimal(2_255.0), true, true, true,
                "Polandia", "Sopot", "00-555", "Malszowska", "5525/55 lok. 54");

        List<Order> orderListBart = new ArrayList<>();
        orderListBart.add(orderforBart);

        User userBart = new User(2L, "Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false,
                null,
                orderListBart);

        //W
        userRepository.save(userAdam);
        userRepository.save(userBart);

        List<User> resultList = userRepository.findAll();
        List<Order> resultListOrders = orderRepository.findAll();

        //T
        assertEquals(2,resultList.size() );
        assertEquals(3,resultListOrders.size() );

        System.out.println(" ======== USER table size: " + resultList.size()  + " ============");
        System.out.println(" ======== ORDER table size: " + resultListOrders.size()  + " ============");
    }

    @Test
    public void productEntityTest(){

        //G
        Product productXbox = new Product(1L, "xbox", "short desc", "long desc", 4.0, 45, ProductCondition.NEW,55.0, true);
        Product productPlayStation = new Product(2L, "xbox", "short desc", "long desc", 4.0, 45, ProductCondition.NEW,55.0, true);
        productRepository.save(productXbox);
        productRepository.save(productPlayStation);

        Group groupElec = new Group("RTV");
        productXbox.setGroup(groupElec);

        //W
        List<Product> prodListaResult = productRepository.findAll();

        //T

        assertEquals(2, prodListaResult.size());
        System.out.println("============ List product Size: " +  prodListaResult.size() + " ====");
    }

    @Test
    public void productEntityAndCartTest(){

        //G
        Product productXbox = new Product(1L, "xbox", "short desc", "long desc", 4.0, 45, ProductCondition.NEW,55.0, true);
        Product productPlayStation = new Product(2L, "xbox", "short desc", "long desc", 4.0, 45, ProductCondition.NEW,55.0, true);

//        Group groupElec = new Group(1L);
//        productXbox.setGroup(groupElec);
//        productPlayStation.setGroup(groupElec); ******************** jest błąd

        Cart cartOne = new Cart(1L, new BigDecimal(14.0), false);

//        Set<Product> productSet = new HashSet<>();
        List<Product> productList = new ArrayList<>();
        productList.add(productXbox);
        productList.add(productPlayStation);
        cartOne.setProducts(productList);
        cartRepository.save(cartOne);

        Cart cartTwo = new Cart(2L, new BigDecimal(14.0), false);

//        Set<Product> productSetTwo = new HashSet<>();
        List<Product> productList1 = new ArrayList<>();
        productList1.add(productXbox);
        productList1.add(productPlayStation);
        cartTwo.setProducts(productList1);
        cartRepository.save(cartTwo);


        //W
//        List<Product> prodListaResult = productRepository.findAll();

        //T

        List<Cart> cartsListInTable = cartRepository.findAll();
//        List<Product>  productRepositoryAll = productRepository.findAll();

        System.out.println("List<Cart> cartsListInTable: " + cartsListInTable.size() + " =================================");
//        System.out.println("List<Product>  productRepositoryAll: " +         productRepositoryAll+ " =================================");

        assertEquals(2, cartsListInTable.size());
//        assertEquals(2, productRepositoryAll.size());

    }


}


package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductCondition;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GroupController groupController;

    @Test
    public void shouldAddProduct() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);

        //When
        productRepository.save(product1);
        List<Product> products = productRepository.findAll();

        //Then
        Assert.assertEquals(1, products.size());

        //Cleanup
        productRepository.deleteById(product1.getId());
    }

    @Test
    public void shouldGetAllProducts() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product2);

        //When
        List<Product> products = productRepository.findAll();

        //Then
        Assert.assertEquals(2, products.size());

        //Cleanup
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
    }

    @Test
    public void shouldFindProductById() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product2);

        //When
        Optional<Product> result = productRepository.findById(product2.getId());

        //Then
        Assert.assertEquals("Product2", result.get().getName());

        //Cleanup
        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());
    }

    @Test
    public void shouldDeleteProductById() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product2);

        //When
        productRepository.deleteById(product1.getId());

        //Then
        Assert.assertEquals(1, productRepository.findAll().size());

        //Cleanup
        productRepository.deleteById(product2.getId());
    }

    @Test
    public void shouldAddProductToCart() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Cart cart = new Cart(new BigDecimal("1000"), false);

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);

        //Then
        Assert.assertEquals(2, cart.getProducts().size());

        //Cleanup
        cartRepository.deleteById(cart.getId());
    }

    @Transactional
    @Test
    public void shouldCartBePresentWhenProductIsDeleted() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product2);
        Cart cart = new Cart(new BigDecimal("1000"), false);

        //When
        cart.getProducts().add(product1);
        cart.getProducts().add(product2);
        cartRepository.save(cart);

        cart.getProducts().remove(product1);
        cart.getProducts().remove(product2);
        cartRepository.save(cart);

        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());

        //Then
        Assert.assertEquals(1, cartRepository.findAll().size());

        //Cleanup
        cartRepository.deleteById(cart.getId());
    }

    @Test
    public void shouldAddProductToGroup() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);

        Group group = new Group();

        //When
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        groupRepository.save(group);

        //Then
        Assert.assertEquals(2, group.getProducts().size());

        //Cleanup
        groupRepository.deleteById(group.getId());
    }

    @Test
    public void shouldGroupBePresentWhenProductIsDeleted() {

        //Given
        Product product1 = new Product("Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product("Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product2);

        Group group = new Group();

        //When
        group.getProducts().add(product1);
        group.getProducts().add(product2);
        groupRepository.save(group);

        productRepository.deleteById(product1.getId());
        productRepository.deleteById(product2.getId());

        group.getProducts().remove(product1);
        group.getProducts().remove(product2);
        groupRepository.save(group);

        //Then
        Assert.assertEquals(1, groupRepository.findAll().size());

        //Cleanup
        groupRepository.deleteById(group.getId());
    }
}

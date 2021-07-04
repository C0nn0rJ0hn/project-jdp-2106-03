package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductCondition;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTestSuite {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldAddProduct() {

        //Given
        Product product1 = new Product(1L, "Product1", "Product1_Short", "Product1_Long",
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
        Product product1 = new Product(1L, "Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product(2L, "Product2", "Product2_Short", "Product2_Long",
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
        Product product1 = new Product(1L, "Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product(2L, "Product2", "Product2_Short", "Product2_Long",
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
        Product product1 = new Product(1L, "Product1", "Product1_Short", "Product1_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product1);
        Product product2 = new Product(2L, "Product2", "Product2_Short", "Product2_Long",
                100, 10, ProductCondition.NEW, 5, true);
        productRepository.save(product2);

        //When
        productRepository.deleteById(product1.getId());

        //Then
        Assert.assertEquals(1, productRepository.findAll().size());

        //Cleanup
        productRepository.deleteById(product2.getId());
    }
}

package com.kodilla.ecommercee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "PRODUCTS")
public class Product {
    private Long id;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

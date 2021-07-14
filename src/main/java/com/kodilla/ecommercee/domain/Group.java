package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"GROUPS\"")
public class Group {

    private Long id;
    private String name;
    private List<Product> products = new ArrayList<>();

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(Long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Group(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Group(String name) {
        this.name = name;
    }

    public Group() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID", unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "GROUP_NAME")
    public String getName() {
        return name;
    }

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            fetch = FetchType.LAZY
    )
    public List<Product> getProducts() {
        return products;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void setName(String name) {
        this.name = name;
    }
}

package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Cart {

    private Long id;
    private BigDecimal cartSum;
    private boolean isCartClosed;
    private Set<Product> products;

    public Cart(Long id, BigDecimal cartSum, boolean isCartClosed) {
        this.id = id;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
    }

    public Cart() {
    }

    @Id
    @Column(unique = true)
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public BigDecimal getCartSum() {
        return cartSum;
    }

    public void setCartSum(BigDecimal cartSum) {
        this.cartSum = cartSum;
    }

    @Column
    public boolean isCartClosed() {
        return isCartClosed;
    }

    public void setCartClosed(boolean cartClosed) {
        isCartClosed = cartClosed;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")}
    )
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

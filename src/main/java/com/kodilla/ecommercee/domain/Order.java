package com.kodilla.ecommercee.domain;

import javax.persistence.*;

@Entity(name = "ORDERS")
public class Order {

    private Long id;
    private User user;

    public Order(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Order() {
    }

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
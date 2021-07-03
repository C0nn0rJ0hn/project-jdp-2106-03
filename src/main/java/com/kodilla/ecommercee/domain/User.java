package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USERS")
public class User {

    private Long id;
    private String name;
    private String lastname;
    private String mail;
    private String phoneNumber;
    private String NIP;
    private boolean isBlocked;
    private Cart cart;
    private List<Order> orders;

    public User(Long id, String name, String lastname, String mail, String phoneNumber, String NIP, boolean isBlocked, Cart cart, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.NIP = NIP;
        this.isBlocked = isBlocked;
        this.cart = cart;
        this.orders = orders;
    }

    public User(Long id, String name, String lastname, String mail, String phoneNumber, String NIP, boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.NIP = NIP;
        this.isBlocked = isBlocked;
    }

    public User() {
    }

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "MAIL")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "NIP")
    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    @Column(name = "IS_BLOCKED")
    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @OneToMany(
            targetEntity = Order.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

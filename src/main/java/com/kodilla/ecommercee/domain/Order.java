package com.kodilla.ecommercee.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "ORDERS")
public class Order {

    private  Long id;
    private  LocalDate orderDate;
    private  String orderNumber;
    private BigDecimal orderTotalPrice;
    private boolean orderIsCompleted;
    private boolean orderIsPaid;
    private boolean orderIsSend;
    private String addressCountry;
    private String addressCity;
    private String addressPost;
    private String addressStreet;
    private String addressBuildNumber;
    private Cart cart;
    private User user;

    public Order(Long id, LocalDate orderDate, String orderNumber, BigDecimal orderTotalPrice, boolean orderIsCompleted, boolean orderIsPaid, boolean orderIsSend, String addressCountry, String addressCity, String addressPost, String addressStreet, String addressBuildNumber) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderNumber = orderNumber;
        this.orderTotalPrice = orderTotalPrice;
        this.orderIsCompleted = orderIsCompleted;
        this.orderIsPaid = orderIsPaid;
        this.orderIsSend = orderIsSend;
        this.addressCountry = addressCountry;
        this.addressCity = addressCity;
        this.addressPost = addressPost;
        this.addressStreet = addressStreet;
        this.addressBuildNumber = addressBuildNumber;
    }

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    public Long getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    public Cart getCart() {
        return cart;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @Column(name = "ORDER_DATE")
    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Column(name = "ORDER_NUMBER")
    public String getOrderNumber() {
        return orderNumber;
    }

    @Column(name = "TOTAL_PRICE_FOR_ORDER")
    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    @Column(name = "IS_COMPLETED")
    public boolean isOrderIsCompleted() {
        return orderIsCompleted;
    }

    @Column(name = "IS_PAID")
    public boolean isOrderIsPaid() {
        return orderIsPaid;
    }

    @Column(name = "IS_SEND")
    public boolean isOrderIsSend() {
        return orderIsSend;
    }

    @Column(name = "ADDRESS_COUNTRY")
    public String getAddressCountry() {
        return addressCountry;
    }

    @Column(name = "ADDRESS_CITY")
    public String getAddressCity() {
        return addressCity;
    }

    @Column(name = "ADDRESS_POST_OFFICE")
    public String getAddressPost() {
        return addressPost;
    }

    @Column(name = "ADDRESS_STREET")
    public String getAddressStreet() {
        return addressStreet;
    }

    @Column(name = "ADDRESS_BUILDING_NUM")
    public String getAddressBuildNumber() {
        return addressBuildNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void setOrderIsCompleted(boolean orderIsCompleted) {
        this.orderIsCompleted = orderIsCompleted;
    }

    public void setOrderIsPaid(boolean orderIsPaid) {
        this.orderIsPaid = orderIsPaid;
    }

    public void setOrderIsSend(boolean orderIsSend) {
        this.orderIsSend = orderIsSend;
    }

    public void setAddressCountry(String addressCountry) { this.addressCountry = addressCountry; }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public void setAddressPost(String addressPost) {
        this.addressPost = addressPost;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public void setAddressBuildNumber(String addressBuildNumber) {
        this.addressBuildNumber = addressBuildNumber;
    }
}

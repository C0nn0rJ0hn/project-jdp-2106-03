package com.kodilla.ecommercee.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDto {

    private final long id;
    private final long userId;
    private final long cartId;
    private final LocalDate orderDate;
    private final String orderNumber;
    private BigDecimal orderTotalPrice;
    private boolean orderIsCompleted;
    private boolean orderIsPaid;
    private boolean orderIsSend;
    private String addressCountry;
    private String addressCity;
    private String addressPost;
    private String addressStreet;
    private String addressBuildNumber;


    public OrderDto(long id, long userId, long cartId, LocalDate orderDate, String orderNumber, BigDecimal orderTotalPrice, boolean orderIsCompleted, boolean orderIsPaid, boolean orderIsSend, String addressCountry, String addressCity, String addressPost, String addressStreet, String addressBuildNumber) {
        this.id = id;
        this.userId = userId;
        this.cartId = cartId;
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

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getCartId() {
        return cartId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public boolean isOrderCompleted() {
        return orderIsCompleted;
    }

    public boolean isOrderPaid() {
        return orderIsPaid;
    }

    public boolean isOrderSend() {
        return orderIsSend;
    }

    public String getAddressCountry() {
        return addressCountry;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public String getAddressPost() {
        return addressPost;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public String getAddressBuildNumber() {
        return addressBuildNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;

        OrderDto orderDto = (OrderDto) o;

        if (id != orderDto.id) return false;
        if (userId != orderDto.userId) return false;
        if (cartId != orderDto.cartId) return false;
        if (orderDate != null ? !orderDate.equals(orderDto.orderDate) : orderDto.orderDate != null) return false;
        return orderNumber != null ? orderNumber.equals(orderDto.orderNumber) : orderDto.orderNumber == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (cartId ^ (cartId >>> 32));
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        return result;
    }
}

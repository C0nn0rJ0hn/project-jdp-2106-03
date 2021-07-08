package com.kodilla.ecommercee.domain.dto;

import com.kodilla.ecommercee.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartDto {
    private long cartId;
    private BigDecimal cartSum;
    private boolean isCartClosed;
    private List<Product> products = new ArrayList<>();
    private long productId;

    public CartDto(long cartId, BigDecimal cartSum, boolean isCartClosed, List<Product> products) {
        this.cartId = cartId;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
        this.products = products;
    }

    public CartDto(long cartId, BigDecimal cartSum, boolean isCartClosed, long productId) {
        this.cartId = cartId;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
        this.productId = productId;
    }

    public CartDto(long cartId, BigDecimal cartSum, boolean isCartClosed, List<Product> products, long productId) {
        this.cartId = cartId;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
        this.products = products;
        this.productId = productId;
    }

    public CartDto(long cartId, BigDecimal cartSum, boolean isCartClosed) {
        this.cartId = cartId;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
    }

    public long getCartId() {
        return cartId;
    }

    public BigDecimal getCartSum() {
        return cartSum;
    }

    public boolean isCartClosed() {
        return isCartClosed;
    }

    public Long getProductId() {
        return productId;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartDto)) return false;

        CartDto cartDto = (CartDto) o;

        if (cartId != cartDto.cartId) return false;
        if (isCartClosed != cartDto.isCartClosed) return false;
        return cartSum != null ? cartSum.equals(cartDto.cartSum) : cartDto.cartSum == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (cartId ^ (cartId >>> 32));
        result = 31 * result + (cartSum != null ? cartSum.hashCode() : 0);
        result = 31 * result + (isCartClosed ? 1 : 0);
        return result;
    }
}

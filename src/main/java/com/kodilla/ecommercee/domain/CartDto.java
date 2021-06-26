package com.kodilla.ecommercee.domain;

import java.math.BigDecimal;

public class CartDto {

    private final long cardId;
    private BigDecimal cartSum;
    private boolean isCartClosed;

    public CartDto(int id, BigDecimal cartSum, boolean isCartClosed) {
        this.cardId = id;
        this.cartSum = cartSum;
        this.isCartClosed = isCartClosed;
    }

    public long getCardId() {
        return cardId;
    }

    public BigDecimal getCartSum() {
        return cartSum;
    }

    public boolean isCartClosed() {
        return isCartClosed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartDto)) return false;

        CartDto cartDto = (CartDto) o;

        if (cardId != cartDto.cardId) return false;
        if (isCartClosed != cartDto.isCartClosed) return false;
        return cartSum != null ? cartSum.equals(cartDto.cartSum) : cartDto.cartSum == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (cardId ^ (cardId >>> 32));
        result = 31 * result + (cartSum != null ? cartSum.hashCode() : 0);
        result = 31 * result + (isCartClosed ? 1 : 0);
        return result;
    }
}

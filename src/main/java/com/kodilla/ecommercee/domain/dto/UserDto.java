package com.kodilla.ecommercee.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private String mail;
    private String phoneNumber;
    private String NIP;
    private boolean isBlocked;
    private String generatedRandomKey;
    private String keyExpirationDate;
    private Long cartId;
    private List<Long> ordersId = new ArrayList<>();

    public UserDto(Long id, String name, String lastname, String mail, String phoneNumber, String NIP, boolean isBlocked,
                   String generatedRandomKey, String keyExpirationDate, Long cartId, List<Long> ordersId) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.NIP = NIP;
        this.isBlocked = isBlocked;
        this.generatedRandomKey = generatedRandomKey;
        this.keyExpirationDate = keyExpirationDate;
        this.cartId = cartId;
        this.ordersId = ordersId;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNIP() {
        return NIP;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public String getGeneratedRandomKey() {
        return generatedRandomKey;
    }

    public String getKeyExpirationDate() {
        return keyExpirationDate;
    }

    public Long getCartId() {
        return cartId;
    }

    public List<Long> getOrdersId() {
        return ordersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return id.equals(userDto.id) && name.equals(userDto.name) && lastname.equals(userDto.lastname) && mail.equals(userDto.mail) && phoneNumber.equals(userDto.phoneNumber) && NIP.equals(userDto.NIP);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

package com.kodilla.ecommercee.domain.dto;

import java.util.Objects;

public class UserDto {

    private Long id;
    private String name;
    private String lastname;
    private String mail;
    private String phoneNumber;
    private String NIP;

    public UserDto(Long id, String name, String lastname, String mail, String phoneNumber, String NIP) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.NIP = NIP;
    }

    public UserDto(String name, String lastname, String mail, String phoneNumber, String NIP) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.NIP = NIP;
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

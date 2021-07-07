package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    private OrderRepository orderRepository;

    public User mapToUser(final UserDto userDto) {
        Cart cart = new Cart();
        cart.setId(userDto.getCartId());
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastname(),
                userDto.getMail(),
                userDto.getPhoneNumber(),
                userDto.getNIP(),
                userDto.isBlocked(),
                userDto.getGeneratedRandomKey(),
                cart,
                userDto.getOrdersId().stream().map(orderRepository::findById).map(Optional::get).collect(Collectors.toList())
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getMail(),
                user.getPhoneNumber(),
                user.getNIP(),
                user.isBlocked(),
                user.getGeneratedRandomKey(),
                user.getKeyExpirationDate(),
                user.getCart().getId(),
                user.getOrders().stream().map(Order::getId).collect(Collectors.toList())
        );
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }
}

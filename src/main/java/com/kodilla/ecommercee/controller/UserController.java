package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.web.bind.annotation.*;

import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    List<UserDto> userList = new ArrayList<>();

    @GetMapping("getUsers")
    public List<UserDto> getUsers() {
        //Fixed return object for test purpose only
        userList.add(new UserDto(1L, "Jan", "Kowalski", "jankowalski@eu.pl", "+48555222111", "88051202587"));
        userList.add(new UserDto(2L, "Anna", "Baziak", "annabaziak@eu.pl", "+48555222333", "870303225147"));
        userList.add(new UserDto(3L, "Lech", "Zimny", "lechzimny@eu.pl", "+48555222444", "75020414231"));
        userList.add(new UserDto(4L, "Marta", "Nowak", "martanowak@eu.pl", "+48555222784", "92040512456"));
        return userList;
    }

    @GetMapping("getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        //Fixed return object for test purpose only
        UserDto UserDto1 = new UserDto(1L, "Jan", "Kowalski", "jankowalski@eu.pl", "+48555222111", "88051202587");
        UserDto UserDto2 = new UserDto(2L, "Anna", "Baziak", "annabaziak@eu.pl", "+48555222333", "870303225147");
        UserDto UserDto3 = new UserDto(3L, "Lech", "Zimny", "lechzimny@eu.pl", "+48555222444", "75020414231");
        UserDto UserDto4 = new UserDto(4L, "Marta", "Nowak", "martanowak@eu.pl", "+48555222784", "92040512456");
        UserDto returnUserDto = new UserDto();

        if (userId == 0) returnUserDto = UserDto1;
        if (userId == 1) returnUserDto = UserDto2;
        if (userId == 2) returnUserDto = UserDto3;
        if (userId == 3) returnUserDto = UserDto4;
        if (userId > 3) throw new UserNotFoundException();


        return returnUserDto;
    }

    @DeleteMapping("deleteUser")
    public void deleteUser(@RequestParam Long userId) {
        userList.removeIf(u -> u.getId() == userId);

    }

    @PutMapping("updateUser")
    public UserDto updateUser(@RequestBody UserDto UserDto) {
        //Fixed return object for test purpose only
        UserDto returnUserDto = new UserDto();
        UserDto UserDto1 = new UserDto(1L, "Jan", "Kowalski", "jankowalski@eu.pl", "+48555222111", "88051202587");
        UserDto UserDto2 = new UserDto(2L, "Anna", "Baziak", "annabaziak@eu.pl", "+48555222333", "870303225147");
        UserDto UserDto3 = new UserDto(3L, "Lech", "Zimny", "lechzimny@eu.pl", "+48555222444", "75020414231");
        UserDto UserDto4 = new UserDto(4L, "Marta", "Nowak", "martanowak@eu.pl", "+48555222784", "92040512456");


        if (UserDto.equals(UserDto1)) returnUserDto = UserDto1;
        if (UserDto.equals(UserDto2)) returnUserDto = UserDto2;
        if (UserDto.equals(UserDto3)) returnUserDto = UserDto3;
        if (UserDto.equals(UserDto4)) returnUserDto = UserDto4;

        return returnUserDto;
    }

    @PutMapping("lockUser")
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    public void lockUser (@RequestParam Long userId) {
        UserDto userDto = userList.get(userList.indexOf(userId));

    }

    @PostMapping("createUser")
    public void createUser(@RequestBody UserDto userDto) {
        userList.add(userDto);
    }


}

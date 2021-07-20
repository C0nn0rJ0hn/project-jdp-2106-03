package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exception.UserNotFoundException;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    public void blockUser(final Long userId) throws UserNotFoundException {
        Optional<User> userToBeBlocked = userRepository.findById(userId);
        User blockedUser = userToBeBlocked.orElseThrow(() -> new UserNotFoundException("User not found"));
        blockedUser.setBlocked(true);
        userRepository.save(blockedUser);
    }

    public void unblockUser(final Long userId) throws UserNotFoundException{
        Optional<User> userToBeUnblocked = userRepository.findById(userId);
        User unblockedUser = userToBeUnblocked.orElseThrow(() -> new UserNotFoundException("User not found"));
        unblockedUser.setBlocked(false);
        userRepository.save(unblockedUser);
    }

    public User generateRandomKey(final Long userId) throws UserNotFoundException {
        final String code = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(code.charAt(rnd.nextInt(code.length())));
        }

        Optional<User> userToHaveKey = userRepository.findById(userId);
        User userWithKey = userToHaveKey.orElseThrow(() -> new UserNotFoundException("User not found"));
        userWithKey.setGeneratedRandomKey(sb.toString());

        LocalDateTime keyExpireDate = LocalDateTime.now().plusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expire = keyExpireDate.format(formatter);
        userWithKey.setKeyExpirationDate(expire);

        userWithKey.setBlocked(false);

        userRepository.save(userWithKey);

        return userWithKey;
    }

    public boolean checkIfKeyHasExpired(final Long userId) throws UserNotFoundException{
        Optional<User> findUser = userRepository.findById(userId);
        User userToBeChecked = findUser.orElseThrow(() -> new UserNotFoundException("User not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String keyExpireDate = userToBeChecked.getKeyExpirationDate();
        LocalDateTime expireDate = LocalDateTime.parse(keyExpireDate, formatter);
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(expireDate)) {
            userToBeChecked.setBlocked(true);
            userRepository.save(userToBeChecked);
            return true;
        }
            return false;
    }
}

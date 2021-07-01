package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    User save(User user);

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long userId);

    void deleteById(Long userId);
}


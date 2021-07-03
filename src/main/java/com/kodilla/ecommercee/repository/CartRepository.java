package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends CrudRepository<Cart, Long> {

    @Override
    Cart save(Cart cart);

    @Override
    List<Cart> findAll();

    @Override
    Optional<Cart> findById(Long cartId);

    void deleteById(Long cartId);
}

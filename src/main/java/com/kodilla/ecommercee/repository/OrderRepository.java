package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Override
    Order save(Order order);

    @Override
    Optional<Order> findById(Long id);

    @Override
    boolean existsById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    List<Order> findAll();

}

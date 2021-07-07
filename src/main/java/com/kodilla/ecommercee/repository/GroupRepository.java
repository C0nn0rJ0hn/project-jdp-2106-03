package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository <Group, Long> {

    @Override
    Group save(Group group);

    @Override
    List<Group> findAll();

    @Override
    Optional<Group> findById(Long id);

    void deleteById(Long id);
}

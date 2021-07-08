package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityTestSuite {

    @Autowired
    public UserRepository userRepository ;

    @Autowired
    public CartRepository cartRepository ;

    @Test
    public void userEntityTest(){
        //G
        User userAdam = new User("Adam", "Test", "som@mail.cc", "+48 555 55 55", "888-888-88-88", false, null,null);
        userRepository.save(userAdam);

        //W
        List<User> resultList = userRepository.findAll();

        //T
        assertEquals(1,resultList.size() );
    }
}

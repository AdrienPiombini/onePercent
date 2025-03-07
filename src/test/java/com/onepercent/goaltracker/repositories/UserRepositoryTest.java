package com.onepercent.goaltracker.repositories;

import com.onepercent.goaltracker.domain.entities.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private User user;

    @BeforeEach
    void setup(){
        user = User.builder()
                .username("Toto")
                .externalId("Fake external Id")
                .created(LocalDateTime.now())
                .updated(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    @Test
    void findAll(){
        var result = userRepository.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void findOne(){
        var foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());

        var existedUser =  userRepository.existsById(user.getId());
        assertTrue(existedUser);
    }

    @Test
    void updatedUser(){
        var newUsername = "Foo Bar";
        user.setUsername(newUsername);
        var result = userRepository.save(user);
        assertEquals(newUsername, result.getUsername());
    }

    @Test
    void deletedUser(){
        userRepository.deleteById(user.getId());
        var result = userRepository.existsById(user.getId());
        assertFalse(result);
    }
}
package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.Utils.ServiceResult;
import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.repositories.UserRepository;
import com.onepercent.goaltracker.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setup(){
        user = User.builder()
                .id(UUID.randomUUID())
                .username("Foo")
                .externalId("Bar")
                .build();
        userRepository.save(user);
    }

    @Test
    void shouldNotCreateUser(){
        var result = userService.createUser(user);
        assertFalse(result.isSuccess());
        assertNull(result.getData());
    }

    @Test
    void shouldCreateUser(){
        var newUser = User.builder()
                .externalId("toto")
                .username("tata")
                .build();

        when(userRepository.save(newUser)).thenReturn(newUser);
        var result = userService.createUser(newUser);

        assertTrue(result.isSuccess());
        assertEquals(result.getData(), newUser);
    }

    @Test
    void shouldNotGetUser(){
        var randomUuid = UUID.randomUUID();

        when(userRepository.findById(randomUuid)).thenReturn(Optional.empty());
        var result = userService.getUser(randomUuid);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldGetUser(){
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        var result = userService.getUser(user.getId());

        assertTrue(result.isSuccess());
        assertEquals(result.getData(), user);
    }

    @Test
    void shouldDeleted(){
        var result = userService.deleteUser(UUID.randomUUID());
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdate(){
        var noExistentUser = User.builder()
                .id(UUID.randomUUID())
                .build();
        var result = userService.updateUser(noExistentUser);

        assertFalse(result.isSuccess());
    }

    @Test
    void shouldUpdated(){
        user.setUsername("New UserName");

        when(userRepository.existsById(user.getId())).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);
        var result =  userService.updateUser(user);

        assertTrue(result.isSuccess());
        assertEquals(result.getData(), user);
    }

}
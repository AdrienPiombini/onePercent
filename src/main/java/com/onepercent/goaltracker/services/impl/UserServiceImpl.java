package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.repositories.UserRepository;
import com.onepercent.goaltracker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void createUser(User user) {
        var result = userRepository.existsById(user.getId());
        if(result) throw new RuntimeException("User already exist");

        log.info("Creation user {}", user.getId());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID uuid) {
        log.info("Deletion of the user {}", uuid);
        userRepository.deleteById(uuid);
    }

    @Override
    public User getUser(UUID uuid) {
        var user = userRepository.findById(uuid);
        if(user.isEmpty()) throw new NullPointerException(String.format("The user %s does not exist", uuid));
        return user.get();
    }
}

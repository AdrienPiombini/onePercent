package com.onepercent.goaltracker.services.impl;

import com.onepercent.goaltracker.Utils.ServiceResult;
import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.repositories.UserRepository;
import com.onepercent.goaltracker.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public ServiceResult<?> createUser(User user) {
        if(user.getId() != null){
            return ServiceResult.error("User already exist");
        }
        log.info("Creation user {}", user);
        var result = userRepository.save(user);
        return ServiceResult.ok(result);
    }

    @Override
    public ServiceResult<User> getUser(UUID uuid) {
        var user = userRepository.findById(uuid);
        return user.map(ServiceResult::ok)
                .orElseGet(() -> ServiceResult.error(String.format("The user %s does not exist", uuid)));
    }

    @Override
    public ServiceResult<?> updateUser(User user) {
        var canUpdate = userExist(user.getId());
        if(!canUpdate) return ServiceResult.error(String.format("The user %s does not exist", user.getId()));
        var result = userRepository.save(user);
        return ServiceResult.ok(result);
    }

    @Override
    public ServiceResult<?> deleteUser(UUID uuid) {
        // no check if exist to respect idempotency pattern
        log.info("Deletion of the user {}", uuid);
        userRepository.deleteById(uuid);
        return ServiceResult.ok(null);
    }

    private void findUserOrThrow(UUID uuid){
        var user = userRepository.findById(uuid);
        if(user.isEmpty()) throw new NullPointerException(String.format("The user %s does not exist", uuid));
    }

    private boolean userExist(UUID uuid){
        return userRepository.existsById(uuid);
    }
}

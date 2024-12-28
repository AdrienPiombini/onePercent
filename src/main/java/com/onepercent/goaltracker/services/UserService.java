package com.onepercent.goaltracker.services;

import com.onepercent.goaltracker.domain.entities.User;

import java.util.UUID;

public interface UserService {
    void createUser(User user);
    void deleteUser(UUID uuid);
    User getUser(UUID uuid);
}

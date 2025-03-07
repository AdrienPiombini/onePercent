package com.onepercent.goaltracker.services;

import com.onepercent.goaltracker.Utils.ServiceResult;
import com.onepercent.goaltracker.domain.entities.User;

import java.util.UUID;

public interface UserService {
    ServiceResult<?> createUser(User user);
    ServiceResult<?> deleteUser(UUID uuid);
    ServiceResult<User> getUser(UUID uuid);
    ServiceResult<?> updateUser(User user);
}

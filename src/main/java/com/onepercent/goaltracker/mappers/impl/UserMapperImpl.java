package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.UserDto;
import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.mappers.GoalMapper;
import com.onepercent.goaltracker.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromDto(UserDto userDto) {
//        List<Goal> goalList = userDto.goalList() != null ? userDto.goalList().stream().map(goalMapper::fromGoalDto).toList() : null;
        UUID uuid = null;
        if(userDto.uuid() != null) {
            uuid = UUID.fromString(userDto.uuid());
        }

        return User.builder()
                .id(uuid)
                .username(userDto.username())
                .externalId(userDto.externalId())
//                .goals(goalList)
                .build();
    }

    @Override
    public UserDto toDto(User user) {
        return new UserDto(user.getId().toString(), user.getUsername(), user.getExternalId());
    }
}

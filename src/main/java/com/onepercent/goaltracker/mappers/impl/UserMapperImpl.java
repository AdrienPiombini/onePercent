package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.GoalDto;
import com.onepercent.goaltracker.domain.dto.UserDto;
import com.onepercent.goaltracker.domain.entities.Goal;
import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.mappers.GoalMapper;
import com.onepercent.goaltracker.mappers.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserMapperImpl implements UserMapper {

    private final GoalMapper goalMapper;
    public UserMapperImpl(final GoalMapper goalMapper){
        this.goalMapper = goalMapper;
    }
    @Override
    public User fromDto(UserDto userDto) {
        List<Goal> goalList = userDto.goalList() != null ? userDto.goalList().stream().map(goalMapper::fromGoalDto).toList() : null;
        UUID uuid = null;
        if(userDto.uuid() != null) {
            uuid = UUID.fromString(userDto.uuid());
        }

        return User.builder()
                .id(uuid)
                .username(userDto.username())
                .goals(goalList)
                .build();
    }

    @Override
    public UserDto toDto(User user) {
        List<GoalDto> goalDtoList = user.getGoals().stream().map(goalMapper::toGoalDto).toList();
        return new UserDto(user.getId().toString(), user.getUsername(), goalDtoList);
    }
}

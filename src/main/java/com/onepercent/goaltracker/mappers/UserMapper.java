package com.onepercent.goaltracker.mappers;

import com.onepercent.goaltracker.domain.dto.UserDto;
import com.onepercent.goaltracker.domain.entities.User;

public interface UserMapper {
    User fromDto(UserDto userDto);

    UserDto toDto(User user);
}

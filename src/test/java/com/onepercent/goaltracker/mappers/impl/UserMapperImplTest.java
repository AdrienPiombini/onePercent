package com.onepercent.goaltracker.mappers.impl;

import com.onepercent.goaltracker.domain.dto.UserDto;
import com.onepercent.goaltracker.domain.entities.User;
import com.onepercent.goaltracker.mappers.UserMapper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserMapperImplTest {

    private final UserMapper userMapper = new UserMapperImpl();

    @Test
    void fromDto() {
        var userDto = new UserDto(UUID.randomUUID().toString(), "Fake Username", "Random external Id");

        var result = userMapper.fromDto(userDto);

        assertThat(result.getId().toString()).isEqualTo(userDto.uuid());
        assertThat(result.getUsername()).isEqualTo(userDto.username());
        assertThat(result.getExternalId()).isEqualTo(userDto.externalId());
    }

    @Test
    void toDto() {
        var user = User.builder()
                .id(UUID.randomUUID())
                .username("Toto")
                .externalId("Fake External id")
                .build();

        var result = userMapper.toDto(user);

        assertThat(result.uuid()).isEqualTo(user.getId().toString());
        assertThat(result.username()).isEqualTo(user.getUsername());
        assertThat(result.externalId()).isEqualTo(user.getExternalId());

    }
}
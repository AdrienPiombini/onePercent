package com.onepercent.goaltracker.controllers;

import com.onepercent.goaltracker.domain.dto.UserDto;
import com.onepercent.goaltracker.mappers.UserMapper;
import com.onepercent.goaltracker.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "users")
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(final UserMapper userMapper, UserService userService){
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        var user = userMapper.fromDto(userDto);
        userService.createUser(user);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UUID uuid){
        var result = userService.getUser(uuid);
        var userDto = userMapper.toDto(result);
        return ResponseEntity.ok(userDto);

    }
}

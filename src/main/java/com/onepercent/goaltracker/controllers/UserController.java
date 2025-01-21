package com.onepercent.goaltracker.controllers;

import com.onepercent.goaltracker.domain.dto.UserDto;
import com.onepercent.goaltracker.mappers.UserMapper;
import com.onepercent.goaltracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "users")
@Validated
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
        var result = userService.createUser(user);
        if( ! result.isSuccess()){
            return ResponseEntity.badRequest().body(result.getMessage());
        }
        return ResponseEntity.status(201).build();
    }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody UUID uuid){
            var result = userService.getUser(uuid);
            if( ! result.isSuccess()){
                return ResponseEntity.badRequest().body(result.getMessage());
            }
            var userDto = userMapper.toDto(result.getData());
            return ResponseEntity.ok(userDto);

        }

}

package com.example.spotify.controllers;

import com.example.spotify.dto.*;
import com.example.spotify.models.user.*;
import com.example.spotify.services.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public UserDTO create(@RequestBody @Valid UserRequest userRequest) {
        return userService.save(userRequest);
    }

    @PostMapping("/add-role")
    public Role createRole(@RequestBody Role role) {
        return userService.save(role);
    }

    @PostMapping("/add-role-to-user")
    public void addRoleToUser(@RequestParam UUID id,
                              @RequestParam String roleName) {
        userService.addRoleToUser(id, roleName);
    }
}

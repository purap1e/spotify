package com.example.spotify.controllers;

import com.example.spotify.dto.UserRequest;
import com.example.spotify.models.user.Role;
import com.example.spotify.models.user.UserSpotify;
import com.example.spotify.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserSpotify> getAll() {
        return userService.getAll();
    }

    @PostMapping("/save")
    public UserSpotify create(@RequestBody @Valid UserRequest userRequest) {
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

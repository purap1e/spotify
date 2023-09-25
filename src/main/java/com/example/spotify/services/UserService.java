package com.example.spotify.services;

import com.example.spotify.dto.UserRequest;
import com.example.spotify.models.user.Role;
import com.example.spotify.models.user.UserSpotify;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserSpotify save(UserRequest userRequest);
    Role save(Role role);
    void addRoleToUser(UUID id, String roleName);
    UserSpotify get(UUID id);
    List<UserSpotify> getAll();
}

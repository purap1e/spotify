package com.example.spotify.services;

import com.example.spotify.models.Role;
import com.example.spotify.models.UserSpotify;

import java.util.UUID;

public interface UserService {
    UserSpotify save(UserSpotify user);
    Role save(Role role);
    void addRoleToUser(UUID id, String roleName);
    UserSpotify get(UUID id);
}

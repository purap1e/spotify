package com.example.spotify.services;

import com.example.spotify.dto.*;
import com.example.spotify.models.user.*;

import java.util.*;

public interface UserService {
    UserDTO save(UserRequest userRequest);
    Role save(Role role);
    void addRoleToUser(UUID id, String roleName);
    UserSpotify get(UUID id);
    List<UserDTO> getAll();
}

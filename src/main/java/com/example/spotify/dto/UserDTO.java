package com.example.spotify.dto;

import com.example.spotify.models.user.*;

import java.util.*;

public record UserDTO(UUID id, String username, List<Role> roles) {}

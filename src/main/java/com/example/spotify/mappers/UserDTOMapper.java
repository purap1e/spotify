package com.example.spotify.mappers;

import com.example.spotify.dto.*;
import com.example.spotify.models.user.*;
import org.springframework.stereotype.*;

import java.util.function.*;

@Service
public class UserDTOMapper implements Function<UserSpotify, UserDTO> {
    @Override
    public UserDTO apply(UserSpotify userSpotify) {
        return new UserDTO(
                userSpotify.getId(),
                userSpotify.getUsername(),
                userSpotify.getRoles()
        );
    }
}

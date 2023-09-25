package com.example.spotify.repos;

import com.example.spotify.models.user.UserSpotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserSpotify, UUID> {
    UserSpotify findByUsername(String username);
    UserSpotify findByEmail(String email);
}

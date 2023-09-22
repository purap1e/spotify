package com.example.spotify.services.impl;

import com.example.spotify.models.Role;
import com.example.spotify.models.UserSpotify;
import com.example.spotify.repos.RoleRepo;
import com.example.spotify.repos.UserRepo;
import com.example.spotify.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public UserSpotify save(UserSpotify user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(UUID id, String roleName) {
        log.info("Adding role {} to user {}", roleName, id);
        UserSpotify user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        Role role = roleRepo.findByName(roleName);
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        } else {
            throw new RuntimeException(String.format("user '%s' already has a role named '%s'", id, roleName));
        }
    }

    @Override
    public UserSpotify get(UUID id) {
        log.info("Fetching user {}", id);
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }
}

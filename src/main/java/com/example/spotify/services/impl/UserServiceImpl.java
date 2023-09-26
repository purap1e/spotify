package com.example.spotify.services.impl;

import com.example.spotify.dto.*;
import com.example.spotify.exceptions.FieldAlreadyExistsException;
import com.example.spotify.mappers.*;
import com.example.spotify.models.enums.*;
import com.example.spotify.models.user.Role;
import com.example.spotify.models.user.UserSpotify;
import com.example.spotify.repos.RoleRepo;
import com.example.spotify.repos.UserRepo;
import com.example.spotify.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static String USERNAME_FIELD = "username";
    private final static String EMAIL_FIELD = "email";

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserDTOMapper userDTOMapper;

    @Override
    public UserDTO save(UserRequest userRequest) {
        UserSpotify userSpotify = new UserSpotify();
        userSpotify.setEmail(userRequest.getEmail());
        userSpotify.setUsername(userRequest.getUsername());
        userSpotify.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        if (userRepo.findByUsername(userRequest.getUsername()) != null) {
            throw new FieldAlreadyExistsException(USERNAME_FIELD, userRequest.getUsername());
        } else if (userRepo.findByEmail(userRequest.getEmail()) != null) {
            throw new FieldAlreadyExistsException(EMAIL_FIELD, userRequest.getEmail());
        }

        log.info("Saving new user {} to the database", userSpotify.getUsername());
        return userDTOMapper.apply(userRepo.save(userSpotify));
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
        Role role = roleRepo.findByName(RoleName.valueOf(roleName));
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

    @Override
    public List<UserDTO> getAll() {
        log.info("Fetching all users from the database");
        return userRepo.findAll().stream().map(userDTOMapper).toList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSpotify userSpotify = userRepo.findByUsername(username);
        if (userSpotify == null) {
            log.info("Username '{}' does not exist, please try again", username);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("Username '{}' found in the database!", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userSpotify.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return new User(userSpotify.getUsername(), userSpotify.getPassword(), authorities);
    }
}

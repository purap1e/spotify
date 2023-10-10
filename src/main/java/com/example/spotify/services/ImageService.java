package com.example.spotify.services;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ImageService {
    ResponseEntity<?> get(UUID id);
}

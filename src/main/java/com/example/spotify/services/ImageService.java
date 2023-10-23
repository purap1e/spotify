package com.example.spotify.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageService {
    ResponseEntity<?> get(UUID id);

    String save(MultipartFile image);
}

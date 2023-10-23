package com.example.spotify.controllers;

import com.example.spotify.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<?> download(@PathVariable UUID id) {
        return imageService.get(id);
    }

    @PostMapping
    public String save(@RequestParam MultipartFile image) {
        return imageService.save(image);
    }
}

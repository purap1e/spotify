package com.example.spotify.services.impl;

import com.example.spotify.helper.Api;
import com.example.spotify.models.Image;
import com.example.spotify.repos.ImageRepo;
import com.example.spotify.services.ImageService;
import com.example.spotify.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepo imageRepo;

    @Override
    public ResponseEntity<?> get(UUID id) {
        log.info("fetching image by id: " + id);
        Image image = imageRepo.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        byte[] imageData = ImageUtils.decompressImage(image.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(imageData);
    }

    @Override
    public String save(MultipartFile image) {
        log.info("Saving new image  to the database");
        Image i = ImageUtils.compressImage(image);
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + Api.GET_IMAGE + imageRepo.save(i).getId();
    }
}

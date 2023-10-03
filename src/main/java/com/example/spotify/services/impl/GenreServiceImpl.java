package com.example.spotify.services.impl;

import com.example.spotify.models.music.Genre;
import com.example.spotify.repos.GenreRepo;
import com.example.spotify.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepo genreRepo;

    public GenreServiceImpl(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    @Override
    public Genre save(Genre genre) {
        log.info("Saving new genre to the database");
        return genreRepo.save(genre);
    }

    @Override
    public Genre get(UUID id) {
        return genreRepo.findById(id).orElseThrow(() -> new RuntimeException("genre not found"));
    }
}

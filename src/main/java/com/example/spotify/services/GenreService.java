package com.example.spotify.services;

import com.example.spotify.models.music.Genre;

import java.util.UUID;

public interface GenreService {
    Genre save(Genre genre);
    Genre get(UUID id);
}

package com.example.spotify.services;

import com.example.spotify.models.music.Genre;

import java.util.*;

public interface GenreService {
    Genre save(Genre genre);
    Genre get(UUID id);
    List<Genre> getAll();
}

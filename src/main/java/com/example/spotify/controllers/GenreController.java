package com.example.spotify.controllers;

import com.example.spotify.models.music.*;
import com.example.spotify.services.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    public Genre save(@RequestBody Genre genre) {
        return genreService.save(genre);
    }

    @GetMapping
    public List<Genre> getAll() {
        return genreService.getAll();
    }
}

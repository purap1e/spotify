package com.example.spotify.controllers;

import com.example.spotify.dto.SingerDTO;
import com.example.spotify.models.music.*;
import com.example.spotify.services.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/v1/singers")
public class SingerController {

    private final SingerService singerService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping
    public List<Singer> getAll() {
        return singerService.getAll();
    }

    @PostMapping("/save")
    public SingerDTO create(@RequestParam String name,
                            @RequestParam MultipartFile image) {
        return singerService.save(name, image);
    }

    @GetMapping("/{id}")
    public Singer get(@PathVariable UUID id) {
        return singerService.get(id);
    }
}

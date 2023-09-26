package com.example.spotify.controllers;

import com.example.spotify.models.music.*;
import com.example.spotify.services.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/singers")
public class SingerController {

    private final SingerService singerService;

    @GetMapping("/pagination/{offset}/{pageSize}")
    public List<Singer> getAll(@RequestParam String name,
                               @PathVariable int offset,
                               @PathVariable int pageSize) {
        return singerService.getAll(name, offset, pageSize);
    }

    @PostMapping("/save")
    public Singer create(@RequestBody Singer singer) {
        return singerService.save(singer);
    }

    @GetMapping("/{id}")
    public Singer get(@PathVariable UUID id) {
        return singerService.get(id);
    }
}

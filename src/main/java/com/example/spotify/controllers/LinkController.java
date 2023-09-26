package com.example.spotify.controllers;

import com.example.spotify.models.*;
import com.example.spotify.services.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/links")
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/save")
    public Link create(@RequestBody Link link) {
        return linkService.save(link);
    }
}

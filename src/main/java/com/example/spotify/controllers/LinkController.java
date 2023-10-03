package com.example.spotify.controllers;

import com.example.spotify.models.music.Link;
import com.example.spotify.services.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/save")
    public Link create(@RequestBody Link link) {
        return linkService.save(link);
    }
}

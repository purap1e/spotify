package com.example.spotify.controllers;

import com.example.spotify.dto.MusicDTO;
import com.example.spotify.dto.MusicRequest;
import com.example.spotify.es_models.ESMusic;
import com.example.spotify.models.music.*;
import com.example.spotify.services.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/musics")
public class MusicController {

    private final MusicService musicService;

    @PostMapping("/save")
    public MusicDTO save(@RequestBody MusicRequest musicRequest) {
        return musicService.save(musicRequest);
    }

    @PutMapping("/{id}")
    public MusicDTO addImage(@PathVariable UUID id,
                             @RequestParam MultipartFile image) {
        return musicService.addImage(id, image);
    }

    @PostMapping("/add-singer")
    public Music addSinger(@RequestParam UUID musicId,
                           @RequestParam UUID singerId) {
        return musicService.addSinger(musicId, singerId);
    }

    @PostMapping("/add-genre")
    public Music addGenre(@RequestParam UUID musicId,
                          @RequestParam UUID genreId) {
        return musicService.addGenre(musicId, genreId);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public List<ESMusic> getAllByNameWithPagination(@RequestParam String name,
                                                    @PathVariable int offset,
                                                    @PathVariable int pageSize) {
        return musicService.getAll(name, offset, pageSize);
    }

    @GetMapping("/with-fields")
    public List<ESMusic> getAllWithFields(@RequestParam String name,
                                          @RequestParam UUID id) {
        return musicService.searchMusicsByMoreThanOneField(name, id);
    }
}

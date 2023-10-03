package com.example.spotify.services.impl;

import com.example.spotify.models.music.Genre;
import com.example.spotify.models.music.Music;
import com.example.spotify.models.music.Singer;
import com.example.spotify.repos.MusicRepo;
import com.example.spotify.services.GenreService;
import com.example.spotify.services.MusicService;
import com.example.spotify.services.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    private final MusicRepo musicRepo;
    private final SingerService singerService;
    private final GenreService genreService;

    public MusicServiceImpl(MusicRepo musicRepo, SingerService singerService, GenreService genreService) {
        this.musicRepo = musicRepo;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    @Override
    public Music save(Music music) {
        log.info("Saving new music to the database");
        return musicRepo.save(music);
    }

    @Override
    public Music addSinger(UUID musicId, UUID singerId) {
        Music music = musicRepo.findById(musicId).orElseThrow(() -> new RuntimeException("music not found"));
        Singer singer = singerService.get(singerId);
        if (music.getSingers().stream().anyMatch(x -> x.getSearchSimilar().equals(singer.getSearchSimilar()))) {
            throw new RuntimeException(String.format("Singer %s already exists in music", singer.getSearchSimilar()));
        }
        music.getSingers().add(singer);
        return musicRepo.save(music);
    }

    @Override
    public Music addGenre(UUID musicId, UUID genreId) {
        Music music = musicRepo.findById(musicId).orElseThrow(() -> new RuntimeException("music not found"));
        Genre genre = genreService.get(genreId);
        if (music.getGenres().stream().anyMatch(x -> x.getName().equals(genre.getName()))) {
            throw new RuntimeException(String.format("Genre %s already exists in music", genre.getName()));
        }
        music.getGenres().add(genre);
        return musicRepo.save(music);
    }

    @Override
    public List<Music> getAll(String name, int offset, int pageSize) {
        return musicRepo.findBySearchSimilar(name, PageRequest.of(offset, pageSize));
    }
}

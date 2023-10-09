package com.example.spotify.services.impl;

import com.example.spotify.es_models.ESMusic;
import com.example.spotify.es_repos.ESMusicRepo;
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

import java.util.*;

@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    private final MusicRepo musicRepo;
    private final ESMusicRepo esMusicRepo;
    private final SingerService singerService;
    private final GenreService genreService;

    public MusicServiceImpl(MusicRepo musicRepo, ESMusicRepo esMusicRepo, SingerService singerService, GenreService genreService) {
        this.musicRepo = musicRepo;
        this.esMusicRepo = esMusicRepo;
        this.singerService = singerService;
        this.genreService = genreService;
    }

    @Override
    public Music save(Music music) {
        log.info("Saving new music to the database");
        Music music1 = musicRepo.save(music);

        ESMusic esMusic = new ESMusic();
        esMusic.setName(music1.getName());
        esMusic.setMusicId(music1.getId());
        esMusicRepo.save(esMusic);
        return music1;
    }

    @Override
    public Music addSinger(UUID musicId, UUID singerId) {
        Music music = musicRepo.findById(musicId).orElseThrow(() -> new RuntimeException("music not found"));
        Singer singer = singerService.get(singerId);
        if (music.getSingers().stream().anyMatch(x -> x.getName().equals(singer.getName()))) {
            throw new RuntimeException(String.format("Singer %s already exists in music", singer.getName()));
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
    public List<ESMusic> getAll(String name, int offset, int pageSize) {
        return esMusicRepo.findByName(name, PageRequest.of(offset, pageSize));
    }
}

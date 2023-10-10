package com.example.spotify.services.impl;

import com.example.spotify.dto.MusicDTO;
import com.example.spotify.dto.MusicRequest;
import com.example.spotify.es_models.ESMusic;
import com.example.spotify.es_repos.ESMusicRepo;
import com.example.spotify.mappers.MusicDTOMapper;
import com.example.spotify.models.music.Genre;
import com.example.spotify.models.music.Music;
import com.example.spotify.models.music.Singer;
import com.example.spotify.repos.MusicRepo;
import com.example.spotify.services.GenreService;
import com.example.spotify.services.MusicService;
import com.example.spotify.services.SingerService;
import com.example.spotify.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicServiceImpl implements MusicService {

    private final MusicRepo musicRepo;
    private final ESMusicRepo esMusicRepo;
    private final SingerService singerService;
    private final GenreService genreService;
    private final MusicDTOMapper musicDTOMapper;


    @Override
    public MusicDTO save(MusicRequest musicRequest, MultipartFile image) {
        log.info("Saving new music to the database");

        Music music = new Music();
        music.setName(musicRequest.getName());
        music.setSingers(musicRequest.getListOfSingersId()
                .stream()
                .map(singerService::get)
                .toList());
        music.setGenres(musicRequest.getListOfGenresId()
                .stream()
                .map(genreService::get)
                .toList());
        music.setDateOfCreation(musicRequest.getDate());
        music.setImage(ImageUtils.compressImage(image));

        Music music1 = musicRepo.save(music);
        ESMusic esMusic = new ESMusic();
        esMusic.setName(music1.getName());
        esMusic.setMusicId(music1.getId());
        esMusicRepo.save(esMusic);
        return musicDTOMapper.apply(music1);
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

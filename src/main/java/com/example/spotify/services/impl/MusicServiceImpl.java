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
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicServiceImpl implements MusicService {

    private final MusicRepo musicRepo;
    private final ESMusicRepo esMusicRepo;
    private final SingerService singerService;
    private final GenreService genreService;
    private final MusicDTOMapper musicDTOMapper;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;


    @Override
    public MusicDTO save(MusicRequest musicRequest) {
        log.info("Saving new music to the database");

        Music music = new Music();
        music.setName(musicRequest.getName());
        music.setSingers(getSingers(musicRequest.getListOfSingersId()));
        music.setGenres(getGenres(musicRequest.getListOfGenresId()));
        music.setAudioUrl(musicRequest.getAudioUrl());
        music.setDateOfCreation(musicRequest.getDate());

        Music music1 = musicRepo.save(music);
        ESMusic esMusic = new ESMusic();
        esMusic.setName(music1.getName());
        esMusic.setMusicId(music1.getId());
        esMusicRepo.save(esMusic);
        return musicDTOMapper.apply(music1);
    }

    private List<Singer> getSingers(List<UUID> uuids) {
        return uuids.stream().map(singerService::get).toList();
    }

    private List<Genre> getGenres(List<UUID> uuids) {
        return uuids.stream().map(genreService::get).toList();
    }

    @Override
    public MusicDTO addImage(UUID id, MultipartFile image) {
        log.info("Fetching music from the database");
        Music music = musicRepo.findById(id).orElseThrow(() -> new RuntimeException("music not found"));
        music.setImage(ImageUtils.compressImage(image));
        return musicDTOMapper.apply(musicRepo.save(music));
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

    @Override
    public List<ESMusic> searchMusicsByMoreThanOneField(String name, UUID id) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery("tutorial")
                        .field("name")
                        .field("musicId")
                        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .build();

        return elasticsearchRestTemplate.search(searchQuery, ESMusic.class).get().map(SearchHit::getContent).collect(Collectors.toList());
    }
}

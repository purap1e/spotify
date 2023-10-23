package com.example.spotify.services;

import com.example.spotify.dto.MusicDTO;
import com.example.spotify.dto.MusicRequest;
import com.example.spotify.es_models.ESMusic;
import com.example.spotify.models.music.Music;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface MusicService {
    MusicDTO save(MusicRequest musicRequest);
    MusicDTO addImage(UUID id, MultipartFile image);
    Music addSinger(UUID musicId, UUID singerId);
    Music addGenre(UUID musicId, UUID genreId);
    List<ESMusic> getAll(String name, int offset, int pageSize);
    List<ESMusic> searchMusicsByMoreThanOneField(String name, UUID id);
}

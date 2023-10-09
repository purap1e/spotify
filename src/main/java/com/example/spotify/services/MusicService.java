package com.example.spotify.services;

import com.example.spotify.es_models.ESMusic;
import com.example.spotify.models.music.Music;
import com.example.spotify.models.music.Singer;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.UUID;

public interface MusicService {
    Music save(Music music);
    Music addSinger(UUID musicId, UUID singerId);
    Music addGenre(UUID musicId, UUID genreId);
    List<ESMusic> getAll(String name, int offset, int pageSize);
}

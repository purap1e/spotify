package com.example.spotify.services;

import com.example.spotify.models.music.Music;
import com.example.spotify.models.music.Singer;

import java.util.List;
import java.util.UUID;

public interface MusicService {
    Music save(Music music);
    Music addSinger(UUID musicId, UUID singerId);
    Music addGenre(UUID musicId, UUID genreId);
    List<Music> getAll(String name, int offset, int pageSize);
}

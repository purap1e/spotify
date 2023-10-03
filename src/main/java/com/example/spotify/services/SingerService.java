package com.example.spotify.services;

import com.example.spotify.models.music.*;

import java.util.*;

public interface SingerService {
    Singer save(Singer singer);
    Singer get(UUID id);
    List<Singer> getAll(String name, int offset, int pageSize);
}

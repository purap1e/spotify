package com.example.spotify.services.impl;

import com.example.spotify.models.music.*;
import com.example.spotify.repos.*;
import com.example.spotify.services.*;
import lombok.extern.slf4j.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Slf4j
public class SingerServiceImpl implements SingerService {

    private final SingerRepo singerRepo;

    public SingerServiceImpl(SingerRepo singerRepo) {
        this.singerRepo = singerRepo;
    }

    @Override
    public Singer save(Singer singer) {
        log.info("Saving new singer to the database");
        return singerRepo.save(singer);
    }

    @Override
    public Singer get(UUID id) {
        return singerRepo.findById(id).orElseThrow(() -> new RuntimeException("singer not found"));
    }

    @Override
    public List<Singer> getAll(String name, int offset, int pageSize) {
        return singerRepo.findBySearchSimilar(name, PageRequest.of(offset, pageSize));
    }
}

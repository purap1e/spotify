package com.example.spotify.services.impl;

import com.example.spotify.dto.SingerDTO;
import com.example.spotify.mappers.SingerDTOMapper;
import com.example.spotify.models.music.Singer;
import com.example.spotify.repos.SingerRepo;
import com.example.spotify.services.SingerService;
import com.example.spotify.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SingerServiceImpl implements SingerService {

    private final SingerRepo singerRepo;
    private final SingerDTOMapper singerDTOMapper;

    @Override
    public SingerDTO save(String name, MultipartFile image) {
        log.info("Saving new singer to the database");
        Singer singer = new Singer();
        singer.setName(name);
        singer.setImage(ImageUtils.compressImage(image));
        return singerDTOMapper.apply(singerRepo.save(singer));
    }

    @Override
    public Singer get(UUID id) {
        return singerRepo.findById(id).orElseThrow(() -> new RuntimeException("singer not found"));
    }

    @Override
    public List<Singer> getAll() {
        return singerRepo.findAll();
    }
}

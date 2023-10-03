package com.example.spotify.services.impl;

import com.example.spotify.models.music.Link;
import com.example.spotify.repos.*;
import com.example.spotify.services.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepo linkRepo;

    @Override
    public Link save(Link link) {
        log.info("Saving new link to the database");
        return linkRepo.save(link);
    }
}

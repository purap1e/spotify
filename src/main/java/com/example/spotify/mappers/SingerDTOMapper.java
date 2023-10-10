package com.example.spotify.mappers;

import com.example.spotify.dto.SingerDTO;
import com.example.spotify.helper.Api;
import com.example.spotify.models.music.Singer;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

@Service
public class SingerDTOMapper implements Function<Singer, SingerDTO> {

    @Override
    public SingerDTO apply(Singer singer) {
        return new SingerDTO(
                singer.getId(),
                singer.getName(),
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + Api.GET_IMAGE + singer.getImage().getId()
        );
    }
}

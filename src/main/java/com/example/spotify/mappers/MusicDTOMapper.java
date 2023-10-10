package com.example.spotify.mappers;

import com.example.spotify.dto.MusicDTO;
import com.example.spotify.helper.Api;
import com.example.spotify.models.music.Genre;
import com.example.spotify.models.music.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MusicDTOMapper implements Function<Music, MusicDTO> {

    private final SingerDTOMapper singerDTOMapper;

    @Override
    public MusicDTO apply(Music music) {
        return new MusicDTO(
                music.getId(),
                music.getName(),
                music.getSingers().stream().map(singerDTOMapper).toList(),
                music.getDateOfCreation(),
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + Api.GET_IMAGE + music.getImage().getId(),
                music.getGenres().stream().map(Genre::getName).toList()
        );
    }
}

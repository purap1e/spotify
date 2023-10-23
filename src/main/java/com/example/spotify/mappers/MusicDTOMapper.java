package com.example.spotify.mappers;

import com.example.spotify.dto.MusicDTO;
import com.example.spotify.helper.Api;
import com.example.spotify.models.Image;
import com.example.spotify.models.music.Genre;
import com.example.spotify.models.music.Music;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class MusicDTOMapper implements Function<Music, MusicDTO> {

    private final SingerDTOMapper singerDTOMapper;
    private UUID id = UUID.fromString("ce099988-f5f3-4e5b-9dde-615facccff0a");

    @Override
    public MusicDTO apply(Music music) {
        Optional<Image> image = Optional.ofNullable(music.getImage());
        if (image.isPresent()) {
            id = music.getImage().getId();
        }

        return new MusicDTO(
                music.getId(),
                music.getName(),
                music.getSingers().stream().map(singerDTOMapper).toList(),
                music.getDateOfCreation(),
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + Api.GET_IMAGE + id,
                music.getAudioUrl(),
                music.getGenres().stream().map(Genre::getName).toList()
        );
    }
}

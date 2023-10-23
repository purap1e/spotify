package com.example.spotify.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record MusicDTO(UUID id, String name, List<SingerDTO> singers, Date dateOfCreation, String imageUrl, String audioUrl, List<String> genres) {
}
